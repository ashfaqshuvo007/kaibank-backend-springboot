package com.kaibank.system.dto.converter;

import com.kaibank.system.dto.model.EmployeeDTO;
import com.kaibank.system.entity.Employee;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The EmployeeConverter class is used to convert employee dto class to employee entity class and vice
 * versa.
 *
 * @author Pabasara
 * @version 1.0
 * @since 1.0
 */
@Component
@NoArgsConstructor
public class EmployeeConverter {

  private ModelMapper modelMapper;

  @Autowired
  public EmployeeConverter(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public Employee employeeDtoToEmployee(EmployeeDTO employeeDTO) {
    return this.modelMapper.map(employeeDTO, Employee.class);
  }

  public EmployeeDTO employeeToEmployeeDto(Employee employee) {
    return this.modelMapper.map(employee, EmployeeDTO.class);
  }
}
