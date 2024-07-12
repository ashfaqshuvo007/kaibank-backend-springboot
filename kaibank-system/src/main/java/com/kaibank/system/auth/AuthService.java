package com.kaibank.system.auth;

import com.kaibank.system.entity.Customer;
import com.kaibank.system.entity.Employee;
import com.kaibank.system.repository.CustomerRepository;
import com.kaibank.system.repository.EmployeeRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The AuthService class is used to handle user authentication related logics.
 *
 * @author Pabasara
 * @version 1.0
 * @since 1.0
 */
@Transactional(propagation = Propagation.REQUIRED)
public class AuthService implements UserDetailsService {

  @Autowired private EmployeeRepository employeeRepository;

  @Autowired private CustomerRepository customerRepository;

  @Transactional(readOnly = true)
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Employee employee = employeeRepository.getEmployeeByUsername(username);
    User user = new User();
    if (employee == null) {
      Customer customer = customerRepository.getCustomerByUsername(username);
      if (customer == null) throw new UsernameNotFoundException("Could not find user");
      else {
        Hibernate.initialize(customer.getRoles());
        user.setName(customer.getName());
        user.setUsername(customer.getUsername());
        user.setPassword(customer.getPassword());
        user.setRoles(customer.getRoles());
      }
    } else {
      Hibernate.initialize(employee.getRoles());
      user.setName(employee.getName());
      user.setUsername(employee.getUsername());
      user.setPassword(employee.getPassword());
      user.setRoles(employee.getRoles());
    }

    if (user == null) {
      throw new UsernameNotFoundException("Could not find user");
    }

    return new KaiBankUserDetails(user);
  }
}
