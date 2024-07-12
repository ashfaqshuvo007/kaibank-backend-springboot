package com.kaibank.system.service;

import com.kaibank.system.dto.model.transaction.TransactionTypesDTO;
import com.kaibank.system.enums.TransactionType;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class TransactionTypeService {
    public TransactionTypesDTO findAllTransactionTypes() {
        TransactionTypesDTO transactionTypesDTO = new TransactionTypesDTO();
        transactionTypesDTO.setTransactionTypes(Arrays.asList(TransactionType.values()));
        return transactionTypesDTO;
    }
}
