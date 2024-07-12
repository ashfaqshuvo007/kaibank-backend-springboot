package com.kaibank.system.controller;

import com.kaibank.system.auth.KaiBankUserDetails;
import com.kaibank.system.dto.BaseResponse;
import com.kaibank.system.dto.model.AccountDTO;
import com.kaibank.system.dto.model.CustomerDTO;
import com.kaibank.system.service.AccountService;
import com.kaibank.system.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;


@RestController
@Slf4j
@RequestMapping("/customers")
// @CrossOrigin(origins = "http://localhost:8081")
public class CustomerController {
    @Autowired private CustomerService customerService;
    @Autowired private AccountService accountService;
    @Autowired private MessageSource messageSource;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<List<CustomerDTO>>> findAllCustomers() {
        List<CustomerDTO> customerDTOList = customerService.findAllCustomers();
        BaseResponse<List<CustomerDTO>> baseResponse =
                new BaseResponse<>(
                        HttpStatus.OK.value(),
                        messageSource.getMessage("customers.retrieved.successfully", null, Locale.getDefault()),
                        customerDTOList);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<CustomerDTO>> findCustomerById(@PathVariable(value = "id") String id) {
        CustomerDTO customerDTO = customerService.findCustomerById(id);
        BaseResponse<CustomerDTO> baseResponse =
                new BaseResponse<CustomerDTO>(
                        HttpStatus.OK.value(),
                        messageSource.getMessage("customers.retrieved.successfully", null, Locale.getDefault()),
                        customerDTO);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<BaseResponse<CustomerDTO>> createCustomer(@RequestBody CustomerDTO customerDto) {

        CustomerDTO customerDTO = customerService.createCustomer(customerDto);

        BaseResponse<CustomerDTO> baseResponse =
                new BaseResponse<>(
                        HttpStatus.OK.value(),
                        messageSource.getMessage("customers.created.successfully", null, Locale.getDefault()),
                        customerDTO);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);

    }

    @PutMapping("/")
    public ResponseEntity<BaseResponse<CustomerDTO>> updateCustomer(@RequestBody CustomerDTO customerDto) {

        CustomerDTO customer = customerService.updateCustomer(customerDto);
        BaseResponse<CustomerDTO> baseResponse =
                new BaseResponse<>(
                        HttpStatus.OK.value(),
                        messageSource.getMessage("customers.updated.successfully", null, Locale.getDefault()),
                        customer);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);

    }

    @DeleteMapping("/")
    public ResponseEntity<BaseResponse<CustomerDTO>> deleteCustomer(@RequestBody CustomerDTO customerDto) {

        CustomerDTO customer = customerService.deleteCustomer(customerDto);
        AccountDTO account = accountService.deleteAccount(customerDto);
        BaseResponse<CustomerDTO> baseResponse =
                new BaseResponse<>(
                        HttpStatus.OK.value(),
                        messageSource.getMessage("customers.created.successfully", null, Locale.getDefault()),
                        customer);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);

    }

    @PostMapping("/block")
    public ResponseEntity<BaseResponse<CustomerDTO>> blockCustomer(@RequestBody CustomerDTO customerDto) {

        CustomerDTO customer = customerService.blockCustomer(customerDto);
        AccountDTO account = accountService.blockAccount(customerDto);
        BaseResponse<CustomerDTO> baseResponse =
                new BaseResponse<>(
                        HttpStatus.OK.value(),
                        messageSource.getMessage("customers.created.successfully", null, Locale.getDefault()),
                        customer);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);

    }

    @PostMapping("/unblock")
    public ResponseEntity<BaseResponse<CustomerDTO>> unblockCustomer(@RequestBody CustomerDTO customerDto) {

        CustomerDTO customer = customerService.unblockCustomer(customerDto);
        AccountDTO account = accountService.unblockAccount(customerDto);
        BaseResponse<CustomerDTO> baseResponse =
                new BaseResponse<>(
                        HttpStatus.OK.value(),
                        messageSource.getMessage("customers.created.successfully", null, Locale.getDefault()),
                        customer);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);

    }

    @GetMapping(value = "/myData", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<CustomerDTO>> findMyData(Authentication authentication) {
        try {
            KaiBankUserDetails userDetails = (KaiBankUserDetails) authentication.getPrincipal();
            CustomerDTO customerDTO = customerService.findMyData(userDetails.getUsername());
            BaseResponse<CustomerDTO> baseResponse =
                    new BaseResponse<CustomerDTO>(
                            HttpStatus.OK.value(),
                            messageSource.getMessage("customers.retrieved.successfully", null, Locale.getDefault()),
                            customerDTO);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (Exception ex) {
            BaseResponse<CustomerDTO> baseResponse =
                    new BaseResponse<CustomerDTO>(
                            HttpStatus.NOT_ACCEPTABLE.value(),
                            ex.getMessage(),
                            null);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);

        }
    }
}

