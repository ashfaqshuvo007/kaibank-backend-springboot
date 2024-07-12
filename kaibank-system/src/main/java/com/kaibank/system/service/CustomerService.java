package com.kaibank.system.service;

import com.kaibank.system.dto.converter.AccountConverter;
import com.kaibank.system.dto.converter.CustomerConverter;
import com.kaibank.system.dto.converter.RoleConverter;
import com.kaibank.system.dto.model.AccountDTO;
import com.kaibank.system.dto.model.CustomerDTO;
import com.kaibank.system.dto.model.EmployeeDTO;
import com.kaibank.system.dto.model.RoleDTO;
import com.kaibank.system.entity.Account;
import com.kaibank.system.entity.Branch;
import com.kaibank.system.entity.Customer;
import com.kaibank.system.entity.Role;
import com.kaibank.system.enums.UserStatus;
import com.kaibank.system.repository.AccountRepository;
import com.kaibank.system.repository.BranchRepository;
import com.kaibank.system.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;


@Service
public class CustomerService {

    @Autowired private CustomerRepository customerRepository;

    @Autowired private AccountRepository accountRepository;

    @Autowired private CustomerConverter customerConverter;

    @Autowired private AccountService accountService;

    @Autowired private AccountConverter accountConverter;

    @Autowired private PasswordEncoder passwordEncoder;

    @Autowired private RoleConverter roleConverter;

    @Autowired private BranchRepository branchRepository;

    @Transactional(readOnly = true)
    public List<CustomerDTO> findAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDTO> customerDTOList = new ArrayList<>();

        if (!CollectionUtils.isEmpty(customers)) {
            customers.forEach(customer -> customerDTOList.add(customerConverter.customerToCustomerDto(customer)));
        }
        return customerDTOList;
    }

    public CustomerDTO findMyData(String userName){
        Customer customer = customerRepository.getCustomerByUsername(userName);
        CustomerDTO customerDTO = customerConverter.customerToCustomerDto(customer);
        Account account = accountRepository.getAccountByOwnerIdNo(customer.getIdNo());
        customerDTO.setAccountDTO(accountConverter.accountToAccountDto(account));
        return customerDTO;
    }
    public CustomerDTO findCustomerById(String id){
        Customer customer = customerRepository.getCustomerByIdNo(id);
        CustomerDTO customerDTO = customerConverter.customerToCustomerDto(customer);
        Account account = accountRepository.getAccountByOwnerIdNo(id);
        customerDTO.setAccountDTO(accountConverter.accountToAccountDto(account));
        return customerDTO;
    }

    @Transactional
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        if (customerRepository.getCustomerByUsername(customerDTO.getUsername()) != null) {
            throw new DuplicateKeyException("This username is taken");
        }
        Branch branch = branchRepository.getBranchById(1L);
        Customer customer = customerConverter.customerDtoToCustomer(customerDTO);
        int password = generateRandomPassword();
        customer.setPassword(passwordEncoder.encode(Integer.toString(password)));
        customer.setFirstLogin(true);
        customer.setAttempts(0);
        Set<Role> roles = convertRoleDtoToEntities(customerDTO.getRoleDTOSet());
        customer.setRoles(roles);
        //customer = customerRepository.save(customer);
        AccountDTO accountDTO = accountService.createAccount(
                customerDTO.getAccountName(),
                customerDTO.getAccountType(),
                branch,
                customer);
        CustomerDTO cusDTO = customerConverter.customerToCustomerDto(customerRepository.getCustomerByIdNo(customer.getIdNo()));
        cusDTO.setPassword(Integer.toString(password));
        cusDTO.setRoleDTOSet(customerDTO.getRoleDTOSet());
        return cusDTO;
    }
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        Customer customer = customerRepository.getCustomerByIdNo(customerDTO.getIdNo());

        if(customerDTO.getName() != null)
            customer.setName(customerDTO.getName());
        if(customerDTO.getAddress() != null)
            customer.setAddress(customerDTO.getAddress());
        if(customerDTO.getPhoneNo() != null)
            customer.setPhoneNo(customerDTO.getPhoneNo());
        if(customerDTO.getDateOfBirth() != null)
            customer.setDateOfBirth(customerDTO.getDateOfBirth());
        if(customerDTO.getEmail() != null)
            customer.setEmail(customerDTO.getEmail());
        if(customerDTO.getPin() != 0)
            customer.setPin(customerDTO.getPin());
        if(customerDTO.getSex() != null)
            customer.setSex(customerDTO.getSex());
        customerRepository.flush();
        return customerDTO;
    }

    public CustomerDTO deleteCustomer(CustomerDTO customerDTO) {
        Customer customer = customerRepository.getCustomerByIdNo(customerDTO.getIdNo());

        customer.setUserStatus(UserStatus.INACTIVE);
        customerRepository.flush();
        return customerDTO;
    }

    public CustomerDTO blockCustomer(CustomerDTO customerDTO) {
        Customer customer = customerRepository.getCustomerByIdNo(customerDTO.getIdNo());

        customer.setUserStatus(UserStatus.BLOCKED);
        customerRepository.flush();
        return customerDTO;
    }

    public CustomerDTO unblockCustomer(CustomerDTO customerDTO) {
        Customer customer = customerRepository.getCustomerByIdNo(customerDTO.getIdNo());

        customer.setUserStatus(UserStatus.ACTIVE);
        customerRepository.flush();
        return customerDTO;
    }

    public int generateRandomPassword() {
        Random r = new Random(System.currentTimeMillis());
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
    }

    public Set<Role> convertRoleDtoToEntities(Set<RoleDTO> roleDTOS) {
        Set<Role> roles = new HashSet<>();
        roleDTOS.forEach(r -> roles.add(roleConverter.roleDtoToRole(r)));
        return roles;
    }

}
