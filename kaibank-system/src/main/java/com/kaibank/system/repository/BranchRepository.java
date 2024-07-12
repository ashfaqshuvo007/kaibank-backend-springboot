package com.kaibank.system.repository;

import com.kaibank.system.entity.Branch;
import com.kaibank.system.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * This BranchRepository class is used to handle database transactions related to branch.
 *
 * @author Pabasara
 * @version 1.0
 * @since 1.0
 */
public interface BranchRepository extends JpaRepository<Branch, Long> {
    @Query("SELECT c FROM Branch c WHERE c.id = :id")
    public Branch getBranchById(@Param("id") Long id);
}
