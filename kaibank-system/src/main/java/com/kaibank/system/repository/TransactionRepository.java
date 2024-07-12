package com.kaibank.system.repository;

import com.kaibank.system.entity.Transaction;
import com.kaibank.system.enums.TransactionStatus;
import com.kaibank.system.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This TransactionRepository class is used to handle database transactions related to transactions.
 *
 * @author Anneli
 * @version 1.0
 * @since 1.0
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction getTransactionByPrevTransId(Long prevTransactionId);

    @Query("SELECT t FROM Transaction t WHERE t.transactionDate <= :transactionDate AND t.transactionType = :transactionType AND t.transactionStatus = :status")
    List<Transaction> getAllPendingTransactionsBefore(LocalDateTime transactionDate,
                                                      TransactionType transactionType,
                                                      TransactionStatus status);
}
