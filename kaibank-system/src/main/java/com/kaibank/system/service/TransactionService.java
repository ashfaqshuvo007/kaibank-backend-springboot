package com.kaibank.system.service;

import com.kaibank.system.dto.converter.*;
import com.kaibank.system.dto.model.AccountDTO;
import com.kaibank.system.dto.model.TransactionDTO;
import com.kaibank.system.entity.*;
import com.kaibank.system.enums.TransactionStatus;
import com.kaibank.system.enums.TransactionType;
import com.kaibank.system.exception.CustomerException;
import com.kaibank.system.exception.TransactionException;
import com.kaibank.system.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This TransactionService class is used to implement logics related to transaction.
 *
 * @author Anneli
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Service
public class TransactionService {

    private final static int RESTRICTION_IN_MINUTES = 10;
    @Autowired
    TransactionConverter transactionConverter;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountConverter accountConverter;
    @Autowired
    BankRepository bankRepository;
    @Autowired
    BankConverter bankConverter;
    @Autowired
    BranchRepository branchRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerConverter customerConverter;
    @Autowired
    BranchConverter branchConverter;
    @Autowired
    RoleService roleService;

    @Transactional
    public TransactionDTO createTransaction(TransactionDTO transactionDTO) throws Exception {
        Account account = accountRepository.getById(transactionDTO.getAccountDTO().getId());
        Branch branch = account.getBranch();
        Bank bank = branch.getBank();
        Customer customer = account.getCustomer();

        double transactionAmount = transactionDTO.getAmount();
        LocalDateTime transactionTime = LocalDateTime.now();

        boolean isDifferentThanDeposit = !transactionDTO.getTransactionType().equals(TransactionType.DEPOSIT);
        if (isDifferentThanDeposit && account.getAvailableAmount() < transactionAmount) {
            throw new Exception("Amount greater than available!");
        } else if (transactionAmount <= 0) {
            throw new Exception("Amount not acceptable!");
        }

        if (transactionDTO.getTransactionType() == TransactionType.WITHDRAW) {
            transactionAmount = -transactionAmount;
        }

        //Changes banks's physical and virtual amount
        changeBanksAmount(bank, transactionAmount, true);

        //Changes recipent's account's amount
        changeAccountAmount(account, transactionAmount, true);

        Transaction transaction = new Transaction();
        transaction.setAmount(Math.abs(transactionAmount));
        transaction.setTransactionType(transactionDTO.getTransactionType());
        transaction.setTransactionDate(transactionTime);
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setAccount(account);
        transaction.setRecipient(null);
        transaction.setMessage(transactionDTO.getMessage());
        transaction.setPrevTransId(transactionDTO.getPrevTransId());
        transaction.setCustomer(customer);
        account.getTransactions().add(transaction);

        Transaction savedTransaction = updateAccountAndGetLastTransaction(account, transactionTime);

        TransactionDTO newTransactionDTO = transactionConverter.transactionToTransactionDto(savedTransaction);
        AccountDTO accountDTO = accountConverter.accountToAccountDto(account);
        accountDTO.setBranchDTO(branchConverter.branchToBranchDto(branch));
        accountDTO.setTransactionDTOSet(getTransactionsDTOSet(account.getTransactions()));

        newTransactionDTO.setAccountDTO(accountConverter.accountToAccountDto(account));

        return newTransactionDTO;
    }

    private Transaction updateAccountAndGetLastTransaction(Account account, LocalDateTime transactionTime) {
        Account updated = accountRepository.save(account);
        return updated.getTransactions()
                .parallelStream()
                .filter(t -> t.getTransactionDate().equals(transactionTime))
                .findFirst().get();
    }

    @Transactional
    public TransactionDTO createTransactionBetweenBanks(TransactionDTO transactionDTO) throws Exception {
        return transferMoneyBetweenBanks(transactionDTO, false);
    }

    private TransactionDTO transferMoneyBetweenBanks(TransactionDTO transactionDTO, boolean isMobile) throws Exception {
        LocalDateTime transactionTime = LocalDateTime.now();
        double transactionAmount = transactionDTO.getAmount();

        Account senderAccount = accountRepository.getById(transactionDTO.getAccountDTO().getId());
        Branch senderBranch = senderAccount.getBranch();
        Bank senderBank = senderBranch.getBank();

        Account recipientAccount = accountRepository.getAccountByIban(transactionDTO.getRecipient().getIban());
        if (Objects.isNull(recipientAccount)) {
            throw new Exception("There is no user with given IBAN!");
        }
        Branch recipientBranch = recipientAccount.getBranch();
        Bank recipientBank = recipientBranch.getBank();

        if (senderAccount.getAvailableAmount() < transactionAmount) {
            throw new Exception("Amount greater than available!");
        } else if (transactionAmount <= 0) {
            throw new Exception("Amount not acceptable!");
        }

        //Changes sender's account amount
        changeAccountAmount(senderAccount, -transactionAmount, true);

        //Changes recipient's account's amount
        changeAccountAmount(recipientAccount, transactionAmount, false);

        //Changes senders banks's virtual amount
        changeBanksAmount(senderBank, transactionAmount, false);

        //Changes recipient's bank's virtual amount
        changeBanksAmount(recipientBank, -transactionAmount, false);

        //Get Updated Account
        senderAccount = accountRepository.getById(transactionDTO.getAccountDTO().getId());
        recipientAccount = accountRepository.getAccountByIban(transactionDTO.getRecipient().getIban());

        Transaction transaction = new Transaction();
        transaction.setTransactionDate(transactionTime);
        transaction.setAmount(Math.abs(transactionAmount));
        transaction.setTransactionType(TransactionType.TRANSFER);
        transaction.setTransactionStatus(TransactionStatus.PENDING);
        transaction.setAccount(senderAccount);
        transaction.setRecipient(recipientAccount);
        transaction.setMessage(transactionDTO.getMessage());
        transaction.setPrevTransId(transactionDTO.getPrevTransId());
        transaction.setCustomer(senderAccount.getCustomer());

        senderAccount.getTransactions().add(transaction);
        Transaction currentTransaction = updateAccountAndGetLastTransaction(senderAccount, transactionTime);
        //Provide less information for recipient account
        TransactionDTO transactionResultDto = transactionConverter.transactionToTransactionDto(currentTransaction);
        AccountDTO recipient = transactionResultDto.getRecipient();

        AccountDTO lessInfoInRecipient = new AccountDTO();
        lessInfoInRecipient.setAccountNo(recipient.getAccountNo());
        lessInfoInRecipient.setAccountType(recipient.getAccountType());
        lessInfoInRecipient.setName(recipient.getName());
        lessInfoInRecipient.setIban(recipient.getIban());
        lessInfoInRecipient.setAvailableAmount(null);
        lessInfoInRecipient.setTotalAmount(null);
        transactionResultDto.setRecipient(lessInfoInRecipient);
        transactionResultDto.setAccountDTO(accountConverter.accountToAccountDto(senderAccount));
        return transactionResultDto;
    }

    private void changeAccountAmount(Account account, Double amount, boolean changeAvailableAmount) {
        account.setTotalAmount(account.getTotalAmount() + amount);
        if (changeAvailableAmount) {
            log.info("Money can be used right now");
            account.setAvailableAmount(account.getAvailableAmount() + amount);
        }
        accountRepository.save(account);
    }

    private void changeBanksAmount(Bank bank, Double amount, Boolean physical) {
        bank.setVirtualAmount(bank.getVirtualAmount() - amount);
        if (physical) bank.setPhysicalAmount(bank.getPhysicalAmount() + amount);
        bankRepository.save(bank);
    }

    private Set<TransactionDTO> getTransactionsDTOSet(Set<Transaction> transactionsSet) {
        Set<TransactionDTO> transactionsTDOSet = new java.util.HashSet<>(Collections.emptySet());

        if (!CollectionUtils.isEmpty(transactionsSet)) {
            transactionsSet.forEach(transaction -> transactionsTDOSet.add(transactionConverter.transactionToTransactionDto(transaction)));
        }
        return transactionsTDOSet;
    }


    @Transactional
    public TransactionDTO modifyTransaction(Long transactionId, String idNo, @Valid TransactionDTO transactionEditDTO) throws Exception {
        log.info("Modify transaction service is called with transactionId {}, idNo{}", transactionId, idNo);
        Transaction oldTransaction = cancelTransactionInternally(transactionId, idNo);
        transactionEditDTO.setPrevTransId(oldTransaction.getId());

        log.info("Modify req type: {}", transactionEditDTO.getTransactionType());
        TransactionDTO newTransaction;
        if (transactionEditDTO.getTransactionType().equals(TransactionType.TRANSFER)) {
            newTransaction = createTransactionBetweenBanks(transactionEditDTO);
        } else {
            newTransaction = createTransaction(transactionEditDTO);
        }
        log.info("Modified successfully");
        return newTransaction;
    }

    @Transactional
    public TransactionDTO cancelTransaction(Long transactionId, String idNo) {
        Transaction transaction = cancelTransactionInternally(transactionId, idNo);
        return transactionConverter.transactionToTransactionDto(transaction);
    }

    public TransactionDTO getTransactionByIdNoAndTransactionId(String idNo, long transactionId) {
        log.info("Get one transaction for idNo: {}, transactionId: {}", idNo, transactionId);
        Account account = accountRepository.getAccountByOwnerIdNo(idNo);
        if (Objects.isNull(account)) {
            throw new CustomerException("Not valid account!");
        }
        Transaction savedTransaction = transactionRepository.getById(transactionId);
        return transactionConverter.transactionToTransactionDto(savedTransaction);
    }

    public List<TransactionDTO> getAllTransactionsByIdNo(String idNo) {
        log.info("Transactions for {}", idNo);
        Account account = accountRepository.getAccountByOwnerIdNo(idNo);
        if (Objects.isNull(account)) {
            throw new CustomerException("Not valid account!");
        }
        Set<Transaction> transactions = account.getTransactions();
        if (Objects.isNull(transactions)) {
            return Collections.emptyList();
        }
        List<TransactionDTO> transactionsDto = transactions
                .stream()
                .sorted((t1, t2) -> t2.getCreatedTime().compareTo(t1.getCreatedTime()))
                .map(t -> transactionConverter.transactionToTransactionDto(t))
                .collect(Collectors.toList());
        log.info("Successfully returned transactions for {}, number of transactions: {}", idNo, transactions.size());
        return transactionsDto;
    }

    private void revertTransferredMoney(Transaction transaction) {
        Double reversalAmount = transaction.getAmount();

        Account senderAccount = transaction.getAccount();
        Bank senderBank = senderAccount.getBranch().getBank();

        Account recipientAccount = transaction.getRecipient();
        Bank recipientBank = recipientAccount.getBranch().getBank();

        //Increase sender's account amount
        changeAccountAmount(senderAccount, reversalAmount, true);

        //Decrease recipient's account's total amount
        changeAccountAmount(recipientAccount, -reversalAmount, false);

        //Increase sender bank's virtual amount
        changeBanksAmount(senderBank, -reversalAmount, false);

        //Decrease recipient bank's virtual amount
        changeBanksAmount(recipientBank, reversalAmount, false);
    }

    private Transaction cancelTransactionInternally(Long transactionId, String idNo) {
        log.info("Cancel transaction for id{},transactionId{}", idNo, transactionId);
        Account account = accountRepository.getAccountByOwnerIdNo(idNo);
        Branch branch = account.getBranch();
        Bank bank = branch.getBank();

        Transaction transaction = transactionRepository.getById(transactionId);

        checkTransactionCanBeCancelled(transaction);

        transaction.setTransactionStatus(TransactionStatus.CANCELLED);
        transaction.setAccount(account);
//        transactionRepository.save(transaction);

        double reversalAmount = transaction.getAmount();

        if (transaction.getTransactionType() == TransactionType.DEPOSIT) {
            reversalAmount = -reversalAmount;
        }

        switch (transaction.getTransactionType()) {
            case WITHDRAW:
            case DEPOSIT:
                //Changes banks's physical and virtual amount
                changeBanksAmount(bank, reversalAmount, true);
                //Changes recipent's account's amount
                changeAccountAmount(account, reversalAmount, true);
                break;
            case TRANSFER:
                revertTransferredMoney(transaction);
        }

        return transactionRepository.getById(transaction.getId());
    }

    private void checkTransactionCanBeCancelled(Transaction transaction) {
        switch (transaction.getTransactionStatus()) {
            case FAILED:
            case CANCELLED:
                throw new TransactionException("Transaction cannot be cancelled");
        }

        boolean isTransfer = transaction.getTransactionType().equals(TransactionType.TRANSFER);
        boolean isTimeExceeded = transaction.getTransactionDate().isBefore(LocalDateTime.now().minusMinutes(RESTRICTION_IN_MINUTES));
        if (isTransfer && isTimeExceeded) {
            throw new TransactionException("Allowed time exceeded for cancelling transfer!");
        }
    }

    @Transactional
    public TransactionDTO createTransferInMobile(TransactionDTO transactionDTO, String username) throws Exception {
        Customer customer = customerRepository.getCustomerByUsername(username);
        Account senderAccount = accountRepository.getById(transactionDTO.getAccountDTO().getId());
        boolean isDifferentCustomer = !senderAccount.getCustomer().equals(customer);
        if (isDifferentCustomer) {
            throw new Exception("Please use your own account id!");
        }
        return transferMoneyBetweenBanks(transactionDTO, true);
    }

    private void increaseAccountAvailableAmount(Transaction transaction) {
        Account recipient = transaction.getRecipient();
        double amount = transaction.getAmount();
        recipient.setAvailableAmount(recipient.getAvailableAmount() + amount);
        accountRepository.save(recipient);
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transactionRepository.save(transaction);
    }

    private List<Transaction> getUncompletedTransfers() {
        // add 2 minutes to overcome parallel modification problem
        LocalDateTime transactionDate = LocalDateTime.now().minusMinutes(RESTRICTION_IN_MINUTES + 2);
        TransactionStatus status = TransactionStatus.PENDING;
        TransactionType type = TransactionType.TRANSFER;
        return transactionRepository.getAllPendingTransactionsBefore(transactionDate, type, status);
    }

    @Transactional
    public void completeTransfers() {
        List<Transaction> uncompletedTransfers = getUncompletedTransfers();
        log.info("Uncompleted transactions fetched: {}", uncompletedTransfers.size());
        for (var transaction : uncompletedTransfers) {
            log.info("Transaction will be completed: {}", transaction.getId());
            increaseAccountAvailableAmount(transaction);
            log.info("Transaction is completed: {}", transaction.getId());
        }
    }
}
