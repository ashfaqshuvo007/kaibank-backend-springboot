package com.kaibank.system.service;

import com.kaibank.system.dto.converter.RoleConverter;
import com.kaibank.system.dto.converter.RoleConverter;
import com.kaibank.system.dto.model.RoleDTO;
import com.kaibank.system.entity.Role;
import com.kaibank.system.repository.RoleRepository;
import com.kaibank.system.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * This RoleService class is used to implement logics related to role.
 *
 * @author Pabasara
 * @version 1.0
 * @since 1.0
 */
@Service
public class RoleService {

  @Autowired private RoleRepository roleRepository;

  @Autowired private RoleConverter roleConverter;

  @Transactional(readOnly = true)
  public List<RoleDTO> findAllRoles() {
    List<Role> roles = roleRepository.findAll();
    List<RoleDTO> roleDTOList = new ArrayList<>();

    if (!CollectionUtils.isEmpty(roles)) {
      roles.forEach(role -> roleDTOList.add(roleConverter.roleToRoleDto(role)));
    }
    return roleDTOList;
  }

  @Transactional(readOnly = true)
  public Set<RoleDTO> rolesSetToRolesDTOSet(Set<Role> roleSet) {
    Set<RoleDTO> roleDTOSet = new java.util.HashSet<>(Collections.emptySet());

    if (!CollectionUtils.isEmpty(roleSet)) {
      roleSet.forEach(role -> roleDTOSet.add(roleConverter.roleToRoleDto(role)));
    }
    return roleDTOSet;
  }
}
