package com.kaibank.system.service;

import com.kaibank.system.dto.converter.AccountConverter;
import com.kaibank.system.dto.converter.CustomerConverter;
import com.kaibank.system.dto.model.AccountDTO;
import com.kaibank.system.dto.model.CustomerDTO;
import com.kaibank.system.entity.Account;
import com.kaibank.system.entity.Branch;
import com.kaibank.system.entity.Customer;
import com.kaibank.system.enums.AccountStatus;
import com.kaibank.system.enums.AccountType;
import com.kaibank.system.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class AccountService {

    @Autowired private AccountRepository accountRepository;

    @Autowired private AccountConverter accountConverter;
    @Autowired private CustomerConverter customerConverter;

    @Transactional(readOnly = true)
    public List<AccountDTO> findAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        List<AccountDTO> accountDTOList = new ArrayList<>();

        if (!CollectionUtils.isEmpty(accounts)) {
            accounts.forEach(account -> accountDTOList.add(accountConverter.accountToAccountDto(account)));
        }
        return accountDTOList;
    }


    public AccountDTO createAccount(String name, AccountType type, Branch branch, Customer customer) {
        String accNo = String.valueOf((int)Math.floor(Math.random()*(100000)));
        Account account = new Account();
        account.setName(name);
        account.setAccountType(type);
        account.setAccountNo(accNo);
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setBranch(branch);
        account.setOpenDate(LocalDateTime.now());
        account.setCustomer(customer);
        account.setIban("EE" + accNo + "000");
        account = accountRepository.save(account);
        return accountConverter.accountToAccountDto(account);
    }

    public AccountDTO deleteAccount(CustomerDTO customer) {
        Account account = accountRepository.getAccountByOwnerIdNo(customer.getIdNo());
        account.setAccountStatus(AccountStatus.INACTIVE);
        return accountConverter.accountToAccountDto(account);
    }

    public AccountDTO blockAccount(CustomerDTO customer) {
        Account account = accountRepository.getAccountByOwnerIdNo(customer.getIdNo());
        account.setAccountStatus(AccountStatus.BLOCKED);
        return accountConverter.accountToAccountDto(account);
    }

    public AccountDTO unblockAccount(CustomerDTO customer) {
        Account account = accountRepository.getAccountByOwnerIdNo(customer.getIdNo());
        account.setAccountStatus(AccountStatus.ACTIVE);
        return accountConverter.accountToAccountDto(account);
    }
}
