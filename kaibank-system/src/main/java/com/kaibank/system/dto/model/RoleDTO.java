package com.kaibank.system.dto.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kaibank.system.enums.RoleType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * The Role DTO class is used to transfer role request/response model data.
 *
 * @author Pabasara
 * @version 1.0
 * @since 1.0
 */
@Component
@Data
@Schema(title = "RoleDTO")
public class RoleDTO implements Serializable {

  @Schema(title = "Role id", description = "Auto generated id for role", required = false)
  @JsonProperty("id")
  private Long id;

  @NotNull(message = "Role type is required")
  @Schema(title = "Role type", required = true)
  @JsonProperty("roleType")
  private RoleType roleType;

  @Schema(title = "Employee DTO models", description = "A set of employee DTO models")
  @JsonProperty("employeeDTOSet")
  private Set<EmployeeDTO> employeeDTOSet;

  @Schema(title = "Customer DTO models", description = "A set of customer DTO models")
  @JsonProperty("customerDTOSet")
  private Set<CustomerDTO> customerDTOSet;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RoleDTO roleDTO = (RoleDTO) o;
    return Objects.equals(id, roleDTO.id)
        && roleType == roleDTO.roleType
        && Objects.equals(employeeDTOSet, roleDTO.employeeDTOSet)
        && Objects.equals(customerDTOSet, roleDTO.customerDTOSet);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, roleType, employeeDTOSet, customerDTOSet);
  }
}
