package com.kaibank.system.controller;

import com.kaibank.system.dto.BaseResponse;
import com.kaibank.system.dto.model.transaction.TransactionTypesDTO;
import com.kaibank.system.service.TransactionTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@Slf4j
@RequestMapping("/transactionTypes")
public class TransactionTypeController {
    @Autowired
    TransactionTypeService transactionTypesService;
    @Autowired
    MessageSource messageSource;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<TransactionTypesDTO>> findAllTransactionTypes() {
        TransactionTypesDTO allTransactionTypes = transactionTypesService.findAllTransactionTypes();
        BaseResponse<TransactionTypesDTO> baseResponse =
                new BaseResponse<>(
                        HttpStatus.OK.value(),
                        messageSource.getMessage("transaction.types.retrieved", null, Locale.getDefault()),
                        allTransactionTypes);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
