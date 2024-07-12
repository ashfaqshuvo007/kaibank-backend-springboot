package com.kaibank.system.controller;

import com.kaibank.system.dto.BaseResponse;
import com.kaibank.system.dto.model.BranchDTO;
import com.kaibank.system.service.BranchService;
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
 * This BranchController class is used to handle request related to branch.
 *
 * @author Pabasara
 * @version 1.0
 * @since 1.0
 */
@RestController
@Slf4j
@RequestMapping("/branches")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BranchController {

  @Autowired private BranchService branchService;

  @Autowired private MessageSource messageSource;

  @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BaseResponse<List<BranchDTO>>> findAllBranches() {
    List<BranchDTO> branchDTOList = branchService.findAllBranches();
    BaseResponse<List<BranchDTO>> baseResponse =
        new BaseResponse<>(
            HttpStatus.OK.value(),
            messageSource.getMessage("branches.retrieved.successfully", null, Locale.getDefault()),
            branchDTOList);
    return new ResponseEntity<>(baseResponse, HttpStatus.OK);
  }
}
