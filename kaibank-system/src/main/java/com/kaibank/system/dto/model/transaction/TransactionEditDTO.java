package com.kaibank.system.dto.model.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kaibank.system.enums.TransactionType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * The Transaction DTO class is used to transfer transaction request/response model data.
 *
 * @author Adil
 * @version 1.0
 * @since 1.0
 */
@Component
@Data
@Schema(title = "TransactionEditDTO")
public class TransactionEditDTO implements Serializable {

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

    @Schema(
            title = "Transaction message",
            description = "Min 5 characters & Max allowed 1000 characters",
            required = false)
    @Size(min = 5, max = 1000)
    @JsonProperty("message")
    private String message;

    @Schema(title = "IBAN")
    @JsonProperty("recipientIban")
    private String recipientIban;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionEditDTO that = (TransactionEditDTO) o;
        return Double.compare(that.amount, amount) == 0
                && Objects.equals(id, that.id)
                && transactionType == that.transactionType
                && Objects.equals(message, that.message)
                && Objects.equals(recipientIban, that.recipientIban);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                transactionType,
                amount,
                message,
                recipientIban);
    }
}
