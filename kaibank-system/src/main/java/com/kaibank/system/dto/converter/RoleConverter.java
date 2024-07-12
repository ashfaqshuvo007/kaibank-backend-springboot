package com.kaibank.system.dto.converter;

import com.kaibank.system.dto.model.RoleDTO;
import com.kaibank.system.entity.Role;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The RoleConverter class is used to convert role dto class to role entity class and vice versa.
 *
 * @author Pabasara
 * @version 1.0
 * @since 1.0
 */
@Component
@NoArgsConstructor
public class RoleConverter {

  private ModelMapper modelMapper;

  @Autowired
  public RoleConverter(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public Role roleDtoToRole(RoleDTO roleDTO) {
    return this.modelMapper.map(roleDTO, Role.class);
  }

  public RoleDTO roleToRoleDto(Role role) {
    return this.modelMapper.map(role, RoleDTO.class);
  }
}
