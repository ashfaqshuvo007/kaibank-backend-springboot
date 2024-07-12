package com.kaibank.system.entity;

import com.kaibank.system.enums.Sex;
import com.kaibank.system.enums.UserStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

/**
 * The Employee entity class is used to define employee attributes and methods.
 *
 * @author Pabasara
 * @version 1.0
 * @since 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
    name = "EMPLOYEE",
    indexes = {@Index(name = "employee_id_pk_index", columnList = "ID")})
public class Employee extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID", unique = true, nullable = false)
  private Long id;

  @Column(name = "NAME", length = 100, nullable = false)
  private String name;

  @Column(name = "USERNAME", length = 20, nullable = false)
  private String username;

  @Column(name = "PASSWORD", length = 1000, nullable = false)
  private String password;

  @Column(name = "DATE_OF_BIRTH", length = 25, nullable = false)
  private LocalDate dateOfBirth;

  @Enumerated(EnumType.STRING)
  @Column(name = "USER_STATUS", nullable = false)
  private UserStatus userStatus;

  @Column(name = "EMAIL", length = 50, nullable = false)
  private String email;

  @Column(name = "IS_FIRST_LOGIN", nullable = false)
  private boolean isFirstLogin;

  @Column(name = "ATTEMPTS", nullable = false)
  private int attempts;

  @Enumerated(EnumType.STRING)
  @Column(name = "SEX", nullable = false)
  private Sex sex;

  @Column(name = "PHONE_NO", length = 20, nullable = false)
  private String phoneNo;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(
      name = "BRANCH_ID",
      foreignKey = @ForeignKey(name = "fk_branch_with_employee"),
      referencedColumnName = "ID")
  private Branch branch;

  @ManyToMany
  @JoinTable(
      name = "EMPLOYEE_ROLE",
      joinColumns = @JoinColumn(name = "employee_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  Set<Role> roles;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Employee employee = (Employee) o;
    return attempts == employee.attempts
        && Objects.equals(id, employee.id)
        && Objects.equals(name, employee.name)
        && Objects.equals(username, employee.username)
        && Objects.equals(password, employee.password)
        && Objects.equals(dateOfBirth, employee.dateOfBirth)
        && userStatus == employee.userStatus
        && Objects.equals(email, employee.email)
        && Objects.equals(isFirstLogin, employee.isFirstLogin)
        && Objects.equals(sex, employee.sex)
        && Objects.equals(phoneNo, employee.phoneNo)
        && Objects.equals(branch, employee.branch)
        && Objects.equals(roles, employee.roles);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        super.hashCode(),
        id,
        name,
        username,
        password,
        dateOfBirth,
        userStatus,
        email,
        isFirstLogin,
        attempts,
        sex,
        phoneNo,
        branch,
        roles);
  }
}
