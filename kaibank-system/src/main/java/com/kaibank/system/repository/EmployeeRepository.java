package com.kaibank.system.repository;

import com.kaibank.system.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * This EmployeeRepository class is used to handle database transactions related to employee.
 *
 * @author Pabasara
 * @version 1.0
 * @since 1.0
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
  @Query("SELECT e FROM Employee e WHERE e.username = :username")
  public Employee getEmployeeByUsername(@Param("username") String username);
}
