package com.kaibank.system.controller;

import com.kaibank.system.dto.BaseResponse;
import com.kaibank.system.dto.model.TransactionDTO;
import com.kaibank.system.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@RestController
@Slf4j
@RequestMapping("/customers/{idNo}/transactions")
public class TransactionManagementController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private MessageSource messageSource;

    @DeleteMapping(value = "/{transaction-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<TransactionDTO>> cancelTransaction(
            @PathVariable("idNo") String idNo,
            @PathVariable("transaction-id") Long transactionId) {
        BaseResponse<TransactionDTO> baseResponse;
        try {
            TransactionDTO transactionDTO = transactionService.cancelTransaction(transactionId, idNo);
            baseResponse = new BaseResponse<>(
                    HttpStatus.OK.value(),
                    messageSource.getMessage("transaction.cancelled.successfully", null, Locale.getDefault()),
                    transactionDTO
            );
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (Exception ex) {
            baseResponse = new BaseResponse<>(
                    HttpStatus.NOT_ACCEPTABLE.value(),
                    ex.getMessage(),
                    null
            );
            return new ResponseEntity<>(baseResponse, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<List<TransactionDTO>>> getAllTransactionsByIdNo(
            @PathVariable("idNo") String idNo) {
        BaseResponse<List<TransactionDTO>> baseResponse;
        try {
            List<TransactionDTO> transactionDTO = transactionService.getAllTransactionsByIdNo(idNo);
            baseResponse = new BaseResponse<>(
                    HttpStatus.OK.value(),
                    messageSource.getMessage("transaction.all.successfully", null, Locale.getDefault()),
                    transactionDTO
            );
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);

        } catch (Exception ex) {
            log.info("Exception message{}", ex.getMessage());
            baseResponse = new BaseResponse<>(
                    HttpStatus.NOT_ACCEPTABLE.value(),
                    messageSource.getMessage("transaction.all.unsuccessfully", null, Locale.getDefault()),
                    null
            );
            return new ResponseEntity<>(baseResponse, HttpStatus.NOT_ACCEPTABLE);
        }
    }


    @PostMapping(value = "/{transaction-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<TransactionDTO>> modifyTransaction(
            @PathVariable("idNo") String idNo,
            @PathVariable("transaction-id") Long transactionId,
            @Valid @RequestBody TransactionDTO transactionEditDTO) {
        BaseResponse<TransactionDTO> baseResponse;
        try {
            TransactionDTO transactionDTO = transactionService.modifyTransaction(transactionId, idNo, transactionEditDTO);
            baseResponse = new BaseResponse<>(
                    HttpStatus.OK.value(),
                    messageSource.getMessage("transaction.modified.successfully", null, Locale.getDefault()),
                    transactionDTO
            );
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);

        } catch (Exception ex) {
            baseResponse = new BaseResponse<>(
                    HttpStatus.NOT_ACCEPTABLE.value(),
                    ex.getMessage(),
                    null
            );
            return new ResponseEntity<>(baseResponse, HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @GetMapping(value = "/{transaction-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<TransactionDTO>> getTransactionByIdNoAndTransactionId(
            @PathVariable("idNo") String idNo,
            @PathVariable("transaction-id") Long transactionId) {
        BaseResponse<TransactionDTO> baseResponse;
        try {
            TransactionDTO transactionDTO = transactionService.getTransactionByIdNoAndTransactionId(idNo, transactionId);
            baseResponse = new BaseResponse<>(
                    HttpStatus.OK.value(),
                    messageSource.getMessage("transaction.one.successfully", null, Locale.getDefault()),
                    transactionDTO
            );
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);

        } catch (Exception ex) {
            baseResponse = new BaseResponse<>(
                    HttpStatus.NOT_ACCEPTABLE.value(),
                    messageSource.getMessage("transaction.one.unsuccessful", null, Locale.getDefault()),
                    null
            );
            return new ResponseEntity<>(baseResponse, HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
