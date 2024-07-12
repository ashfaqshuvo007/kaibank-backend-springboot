package com.kaibank.system.dto.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kaibank.system.entity.BaseEntity;
import com.kaibank.system.enums.TransactionStatus;
import com.kaibank.system.enums.TransactionType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The Transaction DTO class is used to transfer transaction request/response model data.
 *
 * @author Pabasara
 * @version 1.0
 * @since 1.0
 */
@Component
@Data
@Schema(title = "TransactionDTO")
public class TransactionDTO extends BaseEntity implements Serializable{

  @Schema(
      title = "Transaction id",
      description = "Auto generated id for transaction",
      required = false)
  @JsonProperty("id")
  private Long id;

  @NotNull(message = "Transaction type is required")
  @Schema(title = "Type of the transaction", required = true)
  @JsonProperty("transactionType")
  private TransactionType transactionType;

  @NotNull(message = "Amount is required")
  @Schema(title = "Amount of the transaction", required = true)
  @JsonProperty("amount")
  private Double amount;

  @Schema(title = "Date of the transaction", required = false)
  @JsonProperty("transactionDate")
  private LocalDateTime transactionDate = LocalDateTime.now();

  @Schema(title = "Status of the transaction", required = true)
  @JsonProperty("transactionStatus")
  private TransactionStatus transactionStatus;

  @Schema(
      title = "Transaction message",
      description = "Min 5 characters & Max allowed 1000 characters",
      required = false)
  @Size(min = 5, max = 1000)
  @JsonProperty("message")
  private String message;

  @Schema(title = "Recipient account DTO model")
  @JsonProperty("recipient")
  private AccountDTO recipient;

  @Schema(title = "Previous transaction id")
  @JsonProperty("prevTransId")
  private Long prevTransId;

  @Schema(title = "Account DTO model")
  @JsonProperty("accountDTO")
  private AccountDTO accountDTO;

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
    TransactionDTO that = (TransactionDTO) o;
    return Double.compare(that.amount, amount) == 0
        && Objects.equals(id, that.id)
        && transactionType == that.transactionType
        && Objects.equals(transactionDate, that.transactionDate)
        && transactionStatus == that.transactionStatus
        && Objects.equals(message, that.message)
        && Objects.equals(recipient, that.recipient)
        && Objects.equals(prevTransId, that.prevTransId)
        && Objects.equals(accountDTO, that.accountDTO)
        && Objects.equals(customerDTO, that.customerDTO);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        transactionType,
        amount,
        transactionDate,
        transactionStatus,
        message,
        recipient,
        prevTransId,
        accountDTO,
        customerDTO);
  }
}
