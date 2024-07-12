package com.kaibank.system.repository;

import com.kaibank.system.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * This AccountRepository class is used to handle database transactions related to account.
 *
 * @author Pabasara, Anneli
 * @version 1.0
 * @since 1.0
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("SELECT a FROM Account a WHERE a.iban = :iban")
    Account getAccountByIban(String iban);

    @Query("SELECT c FROM Account c WHERE c.customer.idNo = :idNo")
    Account getAccountByOwnerIdNo(@Param("idNo") String idNo);

    @Query("SELECT a FROM Account a WHERE a.customer.id = :customerId")
    public Account getByCustomerId(@Param("customer") Long customerId);

}
