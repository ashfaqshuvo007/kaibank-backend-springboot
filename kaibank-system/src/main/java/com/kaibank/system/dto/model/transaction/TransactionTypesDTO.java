package com.kaibank.system.dto.model.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kaibank.system.enums.TransactionType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "TransactionTypeDTO")
public class TransactionTypesDTO {
    @JsonProperty("transactionTypes")
    private List<TransactionType> transactionTypes;
}
