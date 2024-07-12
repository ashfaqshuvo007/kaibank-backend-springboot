package com.kaibank.system.repository;

import com.kaibank.system.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This BankRepository class is used to handle database transactions related to bank.
 *
 * @author Anneli
 * @version 1.0
 * @since 1.0
 */
public interface BankRepository extends JpaRepository<Bank, Long> {

}
