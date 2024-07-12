package com.kaibank.system.repository;

import com.kaibank.system.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * This CustomerRepository class is used to handle database transactions related to customer.
 *
 * @author Pabasara
 * @version 1.0
 * @since 1.0
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
  @Query("SELECT c FROM Customer c WHERE c.username = :username")
  public Customer getCustomerByUsername(@Param("username") String username);
  @Query("SELECT c FROM Customer c WHERE c.idNo = :idNo")
  public Customer getCustomerByIdNo(@Param("idNo") String idNo);
}
