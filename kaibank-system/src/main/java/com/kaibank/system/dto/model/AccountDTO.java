package com.kaibank.system.dto.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kaibank.system.enums.AccountStatus;
import com.kaibank.system.enums.AccountType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

/**
 * The Account DTO class is used to transfer account request/response model data.
 *
 * @author Pabasara
 * @version 1.0
 * @since 1.0
 */
@Component
@Data
@Schema(title = "AccountDTO")
public class AccountDTO implements Serializable {

  @Schema(title = "Account id", description = "Auto generated id for account")
  @JsonProperty("id")
  private Long id;

  @Schema(
      title = "Name of the account",
      description = "Min 2 characters & Max allowed 20 characters",
      required = true)
  @Size(min = 2, max = 20)
  @JsonProperty("name")
  private String name;

  @Schema(
      title = "Account number",
      description = "Min 2 characters & Max allowed 20 characters",
      required = true)
  @Size(min = 2, max = 20)
  @JsonProperty("accountNo")
  private String accountNo;

  @Schema(
      title = "IBAN",
      description = "Min 2 characters & Max allowed 20 characters",
      required = true)
  @Size(min = 2, max = 20)
  @JsonProperty("iban")
  private String iban;

  @Schema(title = "Account open date", required = true)
  @JsonProperty("openDate")
  private LocalDateTime openDate;

  @Schema(title = "Account type", required = true)
  @JsonProperty("accountType")
  private AccountType accountType;

  @Schema(title = "Account status", required = true)
  @JsonProperty("accountStatus")
  private AccountStatus accountStatus;

  @Schema(title = "Available amount", required = true)
  @JsonProperty("availableAmount")
  private Double availableAmount;

  @Schema(title = "Total amount", required = true)
  @JsonProperty("totalAmount")
  private Double totalAmount;

  @Schema(title = "Branch of the account")
  @JsonProperty("branchDTO")
  private BranchDTO branchDTO;

  @Schema(title = "Transaction DTO models", description = "A set of transaction DTO models")
  @JsonProperty("transactionDTOSet")
  private Set<TransactionDTO> transactionDTOSet;

  @Schema(title = "Customer DTO model")
  @JsonProperty("customerDTO")
  private CustomerDTO customerDTO;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AccountDTO that = (AccountDTO) o;
    return Double.compare(that.availableAmount, availableAmount) == 0
        && Double.compare(that.totalAmount, totalAmount) == 0
        && Objects.equals(id, that.id)
        && Objects.equals(name, that.name)
        && Objects.equals(accountNo, that.accountNo)
        && Objects.equals(iban, that.iban)
        && Objects.equals(openDate, that.openDate)
        && accountType == that.accountType
        && accountStatus == that.accountStatus
        && Objects.equals(branchDTO, that.branchDTO)
        && Objects.equals(transactionDTOSet, that.transactionDTOSet)
        && Objects.equals(customerDTO, that.customerDTO);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        name,
        accountNo,
        iban,
        openDate,
        accountType,
        accountStatus,
        availableAmount,
        totalAmount,
        branchDTO,
        transactionDTOSet,
        customerDTO);
  }
}
