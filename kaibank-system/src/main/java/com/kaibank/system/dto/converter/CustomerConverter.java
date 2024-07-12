package com.kaibank.system.dto.converter;

import com.kaibank.system.dto.model.CustomerDTO;
import com.kaibank.system.entity.Customer;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The CustomerConverter class is used to convert customer dto class to customer entity class and
 * vice versa.
 *
 * @author Pabasara
 * @version 1.0
 * @since 1.0
 */
@Component
@NoArgsConstructor
public class CustomerConverter {

  private ModelMapper modelMapper;

  @Autowired
  public CustomerConverter(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public Customer customerDtoToCustomer(CustomerDTO customerDTO) {
    return this.modelMapper.map(customerDTO, Customer.class);
  }

  public CustomerDTO customerToCustomerDto(Customer customer) {
    return this.modelMapper.map(customer, CustomerDTO.class);
  }
}
