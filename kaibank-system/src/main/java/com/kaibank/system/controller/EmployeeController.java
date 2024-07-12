package com.kaibank.system.controller;

import com.kaibank.system.dto.BaseResponse;
import com.kaibank.system.dto.model.EmployeeDTO;
import com.kaibank.system.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;

/**
 * This EmployeeController class is used to handle request related to employee.
 *
 * @author Boomika
 * @version 1.0
 * @since 1.0
 */
@RestController
@Slf4j
@RequestMapping("/employees")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmployeeController {

  @Autowired private EmployeeService employeeService;

  @Autowired private MessageSource messageSource;

  @PostMapping(
      value = "/register",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BaseResponse<EmployeeDTO>> createEmployee(
      @Valid @RequestBody EmployeeDTO employeeDTO) throws Exception {
    BaseResponse<EmployeeDTO> baseResponse = new BaseResponse<>();
    try {
      EmployeeDTO empDTO = employeeService.saveEmployee(employeeDTO);
      baseResponse =
          new BaseResponse<>(
              HttpStatus.CREATED.value(),
              messageSource.getMessage("employee.created.successfully", null, Locale.getDefault()),
              empDTO);
      return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
    } catch (DuplicateKeyException e) {
      baseResponse =
          new BaseResponse<>(HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage(), employeeDTO);
      return new ResponseEntity<>(baseResponse, HttpStatus.NOT_ACCEPTABLE);
    }
  }
}
