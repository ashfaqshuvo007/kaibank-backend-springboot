package com.kaibank.system.dto.converter;

import com.kaibank.system.dto.model.AccountDTO;
import com.kaibank.system.entity.Account;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The AccountConverter class is used to convert account dto class to account entity class and vice
 * versa.
 *
 * @author Pabasara
 * @version 1.0
 * @since 1.0
 */
@Component
@NoArgsConstructor
public class AccountConverter {

  private ModelMapper modelMapper;

  @Autowired
  public AccountConverter(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public Account accountDtoToAccount(AccountDTO accountDTO) {
    return this.modelMapper.map(accountDTO, Account.class);
  }

  public AccountDTO accountToAccountDto(Account account) {
    return this.modelMapper.map(account, AccountDTO.class);
  }
}
