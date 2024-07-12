package com.kaibank.system.service;

import com.kaibank.system.dto.converter.RoleConverter;
import com.kaibank.system.dto.model.RoleDTO;
import com.kaibank.system.dto.model.UserDTO;
import com.kaibank.system.entity.Customer;
import com.kaibank.system.entity.Employee;
import com.kaibank.system.entity.Role;
import com.kaibank.system.repository.CustomerRepository;
import com.kaibank.system.repository.EmployeeRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * This AuthService class is used to implement logics related to authentication.
 *
 * @author Pabasara
 * @version 1.0
 * @since 1.0
 */
@Service
public class AuthService {

  @Autowired private EmployeeRepository employeeRepository;

  @Autowired private CustomerRepository customerRepository;

  @Autowired private RoleConverter roleConverter;

  @Transactional(readOnly = true)
  public UserDTO getLoggedUserDetails(UserDTO userDTO) {
    Employee employee = employeeRepository.getEmployeeByUsername(userDTO.getUsername());
    UserDTO user = new UserDTO();
    if (employee == null) {
      Customer customer = customerRepository.getCustomerByUsername(userDTO.getUsername());
      if (customer == null) throw new UsernameNotFoundException("Could not find user");
      else {
        Hibernate.initialize(customer.getRoles());
        user.setId(customer.getId());
        user.setCustomerIdNo(customer.getIdNo());
        user.setName(customer.getName());
        user.setUsername(customer.getUsername());
        user.setFirstLogin(customer.isFirstLogin());
        user.setRoleDTOSet(convertRoleEntitiesToDto(customer.getRoles()));
      }
    } else {
      Hibernate.initialize(employee.getRoles());
      user.setId(employee.getId());
      user.setName(employee.getName());
      user.setUsername(employee.getUsername());
      user.setFirstLogin(employee.isFirstLogin());
      user.setRoleDTOSet(convertRoleEntitiesToDto(employee.getRoles()));
    }

    if (user == null) {
      throw new UsernameNotFoundException("Could not find user");
    }

    return user;
  }

  public Set<RoleDTO> convertRoleEntitiesToDto(Set<Role> roles) {
    Set<RoleDTO> roleDTOS = new HashSet<>();
    roles.forEach(r -> roleDTOS.add(roleConverter.roleToRoleDto(r)));
    return roleDTOS;
  }
}
