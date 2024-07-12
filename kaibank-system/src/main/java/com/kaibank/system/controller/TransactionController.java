package com.kaibank.system.controller;

import com.kaibank.system.auth.KaiBankUserDetails;
import com.kaibank.system.dto.BaseResponse;
import com.kaibank.system.dto.model.TransactionDTO;
import com.kaibank.system.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Locale;

/**
 * This TransactonController class is used to handle request related to transaction.
 *
 * @author Anneli
 * @version 1.0
 * @since 1.0
 */
@RestController
@Slf4j
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private MessageSource messageSource;

    @PostMapping(
            value = "/create-transaction",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<TransactionDTO>> createMoneyTransaction(
            @Valid @RequestBody TransactionDTO transactionDTO) throws Exception {
        BaseResponse<TransactionDTO> baseResponse = new BaseResponse<>();
        try {
            TransactionDTO transDTO = transactionService.createTransaction(transactionDTO);
            baseResponse =
                    new BaseResponse<>(
                            HttpStatus.CREATED.value(),
                            messageSource.getMessage("employee.transaction.created.successfully", null, Locale.getDefault()),
                            transDTO);
            return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            baseResponse =
                    new BaseResponse<>(HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage(), transactionDTO);
            return new ResponseEntity<>(baseResponse, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping(
            value = "/create-between-banks-transaction",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<TransactionDTO>> createMoneyTransactionBetweenBanks(
            @Valid @RequestBody TransactionDTO transactionDTO) throws Exception {
        BaseResponse<TransactionDTO> baseResponse = new BaseResponse<>();
        try {
            TransactionDTO transDTO = transactionService.createTransactionBetweenBanks(transactionDTO);
            baseResponse =
                    new BaseResponse<>(
                            HttpStatus.CREATED.value(),
                            messageSource.getMessage("transaction.between.banks.created.successfully", null, Locale.getDefault()),
                            transDTO);
            return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            baseResponse =
                    new BaseResponse<>(HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage(), transactionDTO);
            return new ResponseEntity<>(baseResponse, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping(
            value = "/transfer-in-mobile",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<TransactionDTO>> createTransferInMobile(
            Authentication authentication,
            @Valid @RequestBody TransactionDTO transactionDTO) throws Exception {
        BaseResponse<TransactionDTO> baseResponse = new BaseResponse<>();
        try {
            KaiBankUserDetails userDetails = (KaiBankUserDetails) authentication.getPrincipal();
            TransactionDTO transDTO = transactionService.createTransferInMobile(transactionDTO, userDetails.getUsername());
            baseResponse =
                    new BaseResponse<>(
                            HttpStatus.CREATED.value(),
                            messageSource.getMessage("transaction.between.banks.created.successfully", null, Locale.getDefault()),
                            transDTO);
            return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            baseResponse =
                    new BaseResponse<>(HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage(), transactionDTO);
            return new ResponseEntity<>(baseResponse, HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
