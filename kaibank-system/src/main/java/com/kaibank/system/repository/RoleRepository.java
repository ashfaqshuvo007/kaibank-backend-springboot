package com.kaibank.system.repository;

import com.kaibank.system.entity.Branch;
import com.kaibank.system.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This RoleRepository class is used to handle database transactions related to role.
 *
 * @author Pabasara
 * @version 1.0
 * @since 1.0
 */
public interface RoleRepository extends JpaRepository<Role, Long> {}
