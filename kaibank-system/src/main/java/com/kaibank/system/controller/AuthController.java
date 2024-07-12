package com.kaibank.system.controller;

import com.kaibank.system.dto.BaseResponse;
import com.kaibank.system.dto.model.UserDTO;
import com.kaibank.system.service.AuthService;
import com.kaibank.system.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

/**
 * This AuthController class is used to handle requests related to user authentication.
 *
 * @author Pabasara
 * @version 1.0
 * @since 1.0
 */
@RestController
@Slf4j
@RequestMapping("/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

  @Autowired private RoleService roleService;

  @Autowired private MessageSource messageSource;

  @Autowired private AuthService authService;

  @PostMapping(
      value = "/login",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BaseResponse> login(@RequestBody UserDTO userDTO) {
    UserDTO user = authService.getLoggedUserDetails(userDTO);
    BaseResponse baseResponse =
        new BaseResponse<>(
            HttpStatus.OK.value(),
            messageSource.getMessage("login.success", null, Locale.getDefault()),
            user);
    return new ResponseEntity<>(baseResponse, HttpStatus.OK);
  }

  @GetMapping(value = "/access-denied", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BaseResponse> accessDenied() {
    BaseResponse baseResponse =
        new BaseResponse<>(
            HttpStatus.FORBIDDEN.value(),
            messageSource.getMessage("access.denied", null, Locale.getDefault()),
            null);
    return new ResponseEntity<>(baseResponse, HttpStatus.FORBIDDEN);
  }
}
