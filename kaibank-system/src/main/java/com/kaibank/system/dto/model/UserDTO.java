package com.kaibank.system.dto.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * The User DTO class is used to transfer user request/response model data.
 *
 * @author Pabasara
 * @version 1.0
 * @since 1.0
 */
@Component
@Data
@Schema(title = "UserDTO")
public class UserDTO implements Serializable {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("username")
  private String username;

  @JsonProperty("isFirstLogin")
  private boolean isFirstLogin;

  @JsonProperty("customerIdNo")
  private String customerIdNo;

  @JsonProperty("roleDTOSet")
  Set<RoleDTO> roleDTOSet;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserDTO userDTO = (UserDTO) o;
    return isFirstLogin == userDTO.isFirstLogin
        && Objects.equals(id, userDTO.id)
        && Objects.equals(name, userDTO.name)
        && Objects.equals(username, userDTO.username)
        && Objects.equals(roleDTOSet, userDTO.roleDTOSet)
        && Objects.equals(customerIdNo, userDTO.customerIdNo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, username, isFirstLogin, roleDTOSet, customerIdNo);
  }
}
