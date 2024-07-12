package com.kaibank.system.dto.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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
 * The Employee DTO class is used to transfer employee request/response model data.
 *
 * @author Pabasara
 * @version 1.0
 * @since 1.0
 */
@Component
@Data
@Schema(title = "EmployeeDTO")
public class EmployeeDTO implements Serializable {

  @Schema(title = "Employee id", description = "Auto generated id for employee", required = false)
  @JsonProperty("id")
  private Long id;

  @NotNull(message = "Name is required")
  @Schema(
      title = "Name of the employee",
      description = "Min 2 characters & Max allowed 100 characters",
      required = true)
  @Size(min = 2, max = 100)
  @JsonProperty("name")
  private String name;

  @NotNull(message = "Phone number is required")
  @Schema(
      title = "Phone number of the employee",
      description = "Min 5 characters & Max allowed 20 characters",
      required = true)
  @Size(min = 5, max = 20)
  @JsonProperty("phoneNo")
  private String phoneNo;

  @NotNull(message = "Username is required")
  @Schema(
      title = "Username of the employee",
      description = "Min 6 characters & Max allowed 20 characters",
      required = true)
  @Size(min = 2, max = 20)
  @JsonProperty("username")
  private String username;

  @Schema(
      title = "Password of the employee",
      description = "Min 6 characters & Max allowed 20 characters",
      required = false)
  @Size(min = 6, max = 20)
  @JsonProperty("password")
  private String password;

  @NotNull(message = "Date of birth is required")
  @Schema(title = "Date of birth of the employee (Date pattern is dd-MM-yyyy)", required = true)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  @JsonProperty("dateOfBirth")
  private LocalDate dateOfBirth;

  @NotNull(message = "Employee status is required")
  @Schema(title = "Status of the employee", required = true)
  @JsonProperty("userStatus")
  private UserStatus userStatus;

  @NotNull(message = "Email is required")
  @Schema(
      title = "Email of the employee",
      description = "Min 6 characters & Max allowed 50 characters",
      required = true)
  @Size(min = 6, max = 50)
  @JsonProperty("email")
  private String email;

  @Schema(title = "Login attempts of the employee", required = false)
  @JsonProperty("attempts")
  private int attempts;

  @NotNull(message = "Sex is required")
  @Schema(title = "Sex of the employee", required = true)
  @JsonProperty("sex")
  private Sex sex;

  @Schema(title = "Is this first login of the employee")
  @JsonProperty("isFirstLogin")
  private boolean isFirstLogin;

  @Schema(title = "Role DTO models", description = "A set of role DTO models")
  @JsonProperty("roleDTOSet")
  Set<RoleDTO> roleDTOSet;

  @Schema(title = "Branch DTO model")
  @JsonProperty("branchDTO")
  private BranchDTO branchDTO;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    EmployeeDTO that = (EmployeeDTO) o;
    return attempts == that.attempts
        && Objects.equals(id, that.id)
        && Objects.equals(name, that.name)
        && Objects.equals(phoneNo, that.phoneNo)
        && Objects.equals(username, that.username)
        && Objects.equals(password, that.password)
        && Objects.equals(dateOfBirth, that.dateOfBirth)
        && userStatus == that.userStatus
        && Objects.equals(email, that.email)
        && sex == that.sex
        && isFirstLogin == that.isFirstLogin
        && Objects.equals(roleDTOSet, that.roleDTOSet)
        && Objects.equals(branchDTO, that.branchDTO);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        name,
        phoneNo,
        username,
        password,
        dateOfBirth,
        userStatus,
        email,
        attempts,
        sex,
        isFirstLogin,
        roleDTOSet,
        branchDTO);
  }
}
