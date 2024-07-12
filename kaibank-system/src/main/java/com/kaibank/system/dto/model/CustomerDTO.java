package com.kaibank.system.dto.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kaibank.system.enums.AccountType;
import com.kaibank.system.enums.Sex;
import com.kaibank.system.enums.UserStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

/**
 * The Customer DTO class is used to transfer customer request/response model data.
 *
 * @author Pabasara
 * @version 1.0
 * @since 1.0
 */
@Component
@Data
@Schema(title = "CustomerDTO")
public class CustomerDTO implements Serializable {

  @Schema(title = "Customer id", description = "Auto generated id for customer", required = false)
  @JsonProperty("id")
  private Long id;

  @NotNull(message = "Name is required")
  @Schema(
      title = "Name of the customer",
      description = "Min 2 characters & Max allowed 100 characters",
      required = true)
  @Size(min = 2, max = 100)
  @JsonProperty("name")
  private String name;

  @NotNull(message = "Address is required")
  @Schema(
      title = "Address of the customer",
      description = "Min 5 characters & Max allowed 1000 characters",
      required = true)
  @Size(min = 5, max = 1000)
  @JsonProperty("address")
  private String address;

  @NotNull(message = "Phone number is required")
  @Schema(
      title = "Phone number of the customer",
      description = "Min 5 characters & Max allowed 20 characters",
      required = true)
  @Size(min = 5, max = 20)
  @JsonProperty("phoneNo")
  private String phoneNo;

  @NotNull(message = "Identification number is required")
  @Schema(
      title = "Identification number of the customer",
      description = "Min 5 characters & Max allowed 20 characters",
      required = true)
  @Size(min = 5, max = 20)
  @JsonProperty("idNo")
  private String idNo;

  @NotNull(message = "Username is required")
  @Schema(
      title = "Username of the customer",
      description = "Min 6 characters & Max allowed 20 characters",
      required = true)
  @Size(min = 2, max = 20)
  @JsonProperty("username")
  private String username;

  @Schema(
          title = "Password of the customer",
          description = "Min 6 characters & Max allowed 20 characters",
          required = false)
  @Size(min = 6, max = 20)
  @JsonProperty("password")
  private String password;

  @NotNull(message = "Date of birth is required")
  @Schema(title = "Date of birth of the customer (Date pattern is dd-MM-yyyy)", required = true)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  @JsonProperty("dateOfBirth")
  private LocalDate dateOfBirth;

  @NotNull(message = "Customer status is required")
  @Schema(title = "Status of the customer", required = true)
  @JsonProperty("userStatus")
  private UserStatus userStatus;

  @NotNull(message = "Email is required")
  @Schema(
      title = "Email of the customer",
      description = "Min 6 characters & Max allowed 50 characters",
      required = true)
  @Size(min = 6, max = 50)
  @JsonProperty("email")
  private String email;

  @Schema(title = "Login attempts of the customer", required = false)
  @JsonProperty("attempts")
  private int attempts;

  @Schema(title = "Name of the account", required = false)
  @JsonProperty("accountName")
  private String accountName;

  @Schema(title = "Type of account", required = false)
  @JsonProperty("accountType")
  private AccountType accountType;

  @NotNull(message = "Pin number is required")
  @Schema(
      title = "Pin number of the customer",
      description = "Min 4 characters & Max allowed 6 characters",
      required = true)
  @Size(min = 4, max = 6)
  @JsonProperty("pin")
  private int pin;

  @NotNull(message = "Sex is required")
  @Schema(title = "Sex of the customer", required = true)
  @JsonProperty("sex")
  private Sex sex;

  @Schema(title = "Is this first login of the customer")
  @JsonProperty("isFirstLogin")
  private boolean isFirstLogin;

  @Schema(title = "Account DTO model", description = "Account DTO model")
  @JsonProperty("accountDTO")
  AccountDTO accountDTO;

  @Schema(title = "Role DTO models", description = "A set of role DTO models")
  @JsonProperty("roleDTOSet")
  Set<RoleDTO> roleDTOSet;

  @Schema(title = "Transaction DTO models", description = "A set of transaction DTO models")
  @JsonProperty("transactionDTOS")
  private Set<TransactionDTO> transactionDTOS;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CustomerDTO that = (CustomerDTO) o;
    return attempts == that.attempts
        && pin == that.pin
        && Objects.equals(id, that.id)
        && Objects.equals(name, that.name)
        && Objects.equals(address, that.address)
        && Objects.equals(phoneNo, that.phoneNo)
        && Objects.equals(idNo, that.idNo)
        && Objects.equals(username, that.username)
        && Objects.equals(password, that.password)
        && Objects.equals(dateOfBirth, that.dateOfBirth)
        && userStatus == that.userStatus
        && Objects.equals(email, that.email)
        && sex == that.sex
        && isFirstLogin == that.isFirstLogin
        && Objects.equals(roleDTOSet, that.roleDTOSet)
        && Objects.equals(transactionDTOS, that.transactionDTOS);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        name,
        address,
        phoneNo,
        idNo,
        username,
        password,
        dateOfBirth,
        userStatus,
        email,
        attempts,
        pin,
        sex,
        isFirstLogin,
        roleDTOSet,
        transactionDTOS);
  }
}
