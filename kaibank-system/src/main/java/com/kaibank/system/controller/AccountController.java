package com.kaibank.system.controller;

import com.kaibank.system.dto.BaseResponse;
import com.kaibank.system.dto.model.AccountDTO;
import com.kaibank.system.service.AccountService;
import com.kaibank.system.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

/**
 * This AccountController class is used to handle request related to role.
 *
 * @author Pabasara
 * @version 1.0
 * @since 1.0
 */
@RestController
@Slf4j
@RequestMapping("/accounts")
// @CrossOrigin(origins = "http://localhost:4200")
public class AccountController {

  @Autowired private AccountService accountService;

  @Autowired private MessageSource messageSource;

  @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BaseResponse<List<AccountDTO>>> findAllAccounts() {
    List<AccountDTO> accountDTOList = accountService.findAllAccounts();
    BaseResponse<List<AccountDTO>> baseResponse =
        new BaseResponse<>(
            HttpStatus.OK.value(),
            messageSource.getMessage("roles.retrieved.successfully", null, Locale.getDefault()),
            accountDTOList);
    return new ResponseEntity<>(baseResponse, HttpStatus.OK);
  }
}
