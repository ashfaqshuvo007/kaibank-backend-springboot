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
 * The Bank DTO class is used to transfer bank request/response model data.
 *
 * @author Pabasara
 * @version 1.0
 * @since 1.0
 */
@Component
@Data
@Schema(title = "BankDTO")
public class BankDTO implements Serializable {

  @Schema(title = "Bank id", description = "Auto generated id for bank", required = false)
  @JsonProperty("id")
  private Long id;

  @NotNull(message = "Name is required")
  @Schema(
      title = "Name of the bank",
      description = "Min 2 characters & Max allowed 100 characters",
      required = true)
  @Size(min = 2, max = 100)
  @JsonProperty("name")
  private String name;

  @NotNull(message = "Address is required")
  @Schema(
      title = "Address of the bank",
      description = "Min 5 characters & Max allowed 1000 characters",
      required = true)
  @Size(min = 5, max = 1000)
  @JsonProperty("address")
  private String address;

  @NotNull(message = "Physical amount is required")
  @Schema(title = "Physical amount of the bank", required = true)
  @JsonProperty("physicalAmount")
  private Double physicalAmount;

  @NotNull(message = "Virtual amount is required")
  @Schema(title = "Virtual amount of the bank", required = true)
  @JsonProperty("virtualAmount")
  private Double virtualAmount;

  @Schema(title = "Branch DTO models", description = "A set of branch DTO models")
  @JsonProperty("branchDTOSet")
  private Set<BranchDTO> branchDTOSet;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BankDTO bankDTO = (BankDTO) o;
    return Objects.equals(id, bankDTO.id)
        && Objects.equals(name, bankDTO.name)
        && Objects.equals(address, bankDTO.address)
        && Objects.equals(physicalAmount, bankDTO.physicalAmount)
        && Objects.equals(virtualAmount, bankDTO.virtualAmount)
        && Objects.equals(branchDTOSet, bankDTO.branchDTOSet);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, address, physicalAmount, virtualAmount, branchDTOSet);
  }
}
