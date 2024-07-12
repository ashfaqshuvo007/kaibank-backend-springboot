package com.kaibank.system.dto.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * The Branch DTO class is used to transfer branch request/response model data.
 *
 * @author Pabasara
 * @version 1.0
 * @since 1.0
 */
@Component
@Data
@Schema(title = "BranchDTO")
public class BranchDTO implements Serializable {

  @Schema(title = "Branch id", description = "Auto generated id for branch", required = false)
  @JsonProperty("id")
  private Long id;

  @NotNull(message = "Name is required")
  @Schema(
      title = "Name of the branch",
      description = "Min 2 characters & Max allowed 100 characters",
      required = true)
  @Size(min = 2, max = 100)
  @JsonProperty("name")
  private String name;

  @NotNull(message = "Address is required")
  @Schema(
      title = "Address of the branch",
      description = "Min 5 characters & Max allowed 1000 characters",
      required = true)
  @Size(min = 5, max = 1000)
  @JsonProperty("address")
  private String address;

  @Schema(title = "Bank DTO models")
  @JsonProperty("bankDTO")
  private BankDTO bankDTO;

  @Schema(title = "Employee DTO models", description = "A set of employee DTO models")
  @JsonProperty("employeeDTOSet")
  private Set<EmployeeDTO> employeeDTOSet;

  @Schema(title = "Account DTO models", description = "A set of account DTO models")
  @JsonProperty("accountDTOSet")
  private Set<AccountDTO> accountDTOSet;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BranchDTO branchDTO = (BranchDTO) o;
    return Objects.equals(id, branchDTO.id)
        && Objects.equals(name, branchDTO.name)
        && Objects.equals(address, branchDTO.address)
        && Objects.equals(bankDTO, branchDTO.bankDTO)
        && Objects.equals(employeeDTOSet, branchDTO.employeeDTOSet)
        && Objects.equals(accountDTOSet, branchDTO.accountDTOSet);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, address, bankDTO, employeeDTOSet, accountDTOSet);
  }
}
