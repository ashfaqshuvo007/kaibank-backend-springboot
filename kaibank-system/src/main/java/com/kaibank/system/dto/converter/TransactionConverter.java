package com.kaibank.system.dto.converter;

import com.kaibank.system.dto.model.TransactionDTO;
import com.kaibank.system.entity.Transaction;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The TransactionConverter class is used to convert transaction dto class to transaction entity
 * class and vice versa.
 *
 * @author Pabasara
 * @version 1.0
 * @since 1.0
 */
@Component
@NoArgsConstructor
public class TransactionConverter {

  private ModelMapper modelMapper;

  @Autowired
  public TransactionConverter(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public Transaction transactionDtoToTransaction(TransactionDTO transactionDTO) {
    return this.modelMapper.map(transactionDTO, Transaction.class);
  }

  public TransactionDTO transactionToTransactionDto(Transaction transaction) {
    return this.modelMapper.map(transaction, TransactionDTO.class);
  }
}
