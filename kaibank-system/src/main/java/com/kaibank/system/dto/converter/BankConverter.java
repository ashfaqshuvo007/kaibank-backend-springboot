package com.kaibank.system.dto.converter;

import com.kaibank.system.dto.model.BankDTO;
import com.kaibank.system.entity.Bank;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The BankConverter class is used to convert bank dto class to bank entity class and vice versa.
 *
 * @author Pabasara
 * @version 1.0
 * @since 1.0
 */
@Component
@NoArgsConstructor
public class BankConverter {

  private ModelMapper modelMapper;

  @Autowired
  public BankConverter(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public Bank bankDtoToBank(BankDTO bankDTO) {
    return this.modelMapper.map(bankDTO, Bank.class);
  }

  public BankDTO bankToBankDto(Bank bank) {
    return this.modelMapper.map(bank, BankDTO.class);
  }
}
