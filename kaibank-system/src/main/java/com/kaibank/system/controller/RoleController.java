package com.kaibank.system.controller;

import com.kaibank.system.dto.BaseResponse;
import com.kaibank.system.dto.model.RoleDTO;
import com.kaibank.system.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

/**
 * This RoleController class is used to handle request related to role.
 *
 * @author Pabasara
 * @version 1.0
 * @since 1.0
 */
@RestController
@Slf4j
@RequestMapping("/roles")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RoleController {

  @Autowired private RoleService roleService;

  @Autowired private MessageSource messageSource;

  @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BaseResponse<List<RoleDTO>>> findAllRoles() {
    List<RoleDTO> roleDTOList = roleService.findAllRoles();
    BaseResponse<List<RoleDTO>> baseResponse =
        new BaseResponse<>(
            HttpStatus.OK.value(),
            messageSource.getMessage("roles.retrieved.successfully", null, Locale.getDefault()),
            roleDTOList);
    return new ResponseEntity<>(baseResponse, HttpStatus.OK);
  }
}
