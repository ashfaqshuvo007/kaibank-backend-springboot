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
 * The Customer entity class is used to define customer attributes and methods.
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
    name = "CUSTOMER",
    indexes = {@Index(name = "customer_id_pk_index", columnList = "ID")},
    uniqueConstraints = {@UniqueConstraint(name = "UNI_ID_NO", columnNames = "ID_NO")})
public class Customer extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID", unique = true, nullable = false)
  private Long id;

  @Column(name = "NAME", length = 100, nullable = false)
  private String name;

  @Column(name = "ADDRESS", length = 1000, nullable = false)
  private String address;

  @Column(name = "PHONE_NO", length = 20, nullable = false)
  private String phoneNo;

  @Column(name = "ID_NO", length = 20, nullable = false)
  private String idNo;

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

  @Column(name = "ATTEMPTS", nullable = true)
  private int attempts;

  @Column(name = "PIN", length = 6, nullable = false)
  private int pin;

  @Enumerated(EnumType.STRING)
  @Column(name = "SEX", nullable = false)
  private Sex sex;

  @Column(name = "IS_FIRST_LOGIN", nullable = false)
  private boolean isFirstLogin;

  @ManyToMany
  @JoinTable(
      name = "CUSTOMER_ROLE",
      joinColumns = @JoinColumn(name = "customer_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  Set<Role> roles;

  @OneToMany(
      fetch = FetchType.LAZY,
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      mappedBy = "customer")
  private Set<Transaction> transactions;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Customer customer = (Customer) o;
    return attempts == customer.attempts
        && pin == customer.pin
        && Objects.equals(id, customer.id)
        && Objects.equals(name, customer.name)
        && Objects.equals(address, customer.address)
        && Objects.equals(phoneNo, customer.phoneNo)
        && Objects.equals(idNo, customer.idNo)
        && Objects.equals(username, customer.username)
        && Objects.equals(password, customer.password)
        && Objects.equals(dateOfBirth, customer.dateOfBirth)
        && userStatus == customer.userStatus
        && Objects.equals(email, customer.email)
        && sex == customer.sex
        && isFirstLogin == customer.isFirstLogin
        && Objects.equals(roles, customer.roles);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        super.hashCode(),
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
        roles);
  }

}
