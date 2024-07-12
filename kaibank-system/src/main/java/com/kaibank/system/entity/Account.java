package com.kaibank.system.entity;

import com.kaibank.system.enums.AccountStatus;
import com.kaibank.system.enums.AccountType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The Account entity class is used to define account attributes and methods.
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
    name = "ACCOUNT",
    indexes = {@Index(name = "account_id_pk_index", columnList = "ID")},
    uniqueConstraints = {
      @UniqueConstraint(name = "UNI_ACCOUNT_NO", columnNames = "ACCOUNT_NO"),
      @UniqueConstraint(name = "UNI_IBAN", columnNames = "IBAN")
    })
public class Account extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID", unique = true, nullable = false)
  private Long id;

  @Column(name = "NAME", length = 20, nullable = false)
  private String name;

  @Column(name = "ACCOUNT_NO", length = 20, nullable = false)
  private String accountNo;

  @Column(name = "IBAN", length = 20, nullable = false)
  private String iban;

  @Column(name = "OPEN_DATE", length = 25, nullable = false)
  private LocalDateTime openDate;

  @Enumerated(EnumType.STRING)
  @Column(name = "ACCOUNT_TYPE", nullable = false)
  private AccountType accountType;

  @Enumerated(EnumType.STRING)
  @Column(name = "ACCOUNT_STATUS", nullable = false)
  private AccountStatus accountStatus;

  @Column(name = "AVAILABLE_AMOUNT", nullable = false)
  private double availableAmount;

  @Column(name = "TOTAL_AMOUNT", nullable = false)
  private double totalAmount;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(
      name = "BRANCH_ID",
      foreignKey = @ForeignKey(name = "fk_branch_with_account"),
      referencedColumnName = "ID")
  private Branch branch;

  @OneToMany(
      fetch = FetchType.LAZY,
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      mappedBy = "account")
  private Set<Transaction> transactions = new HashSet<>();

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "customer_id", referencedColumnName = "ID")
  private Customer customer;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Account account = (Account) o;
    return Double.compare(account.availableAmount, availableAmount) == 0
        && Double.compare(account.totalAmount, totalAmount) == 0
        && Objects.equals(id, account.id)
        && Objects.equals(name, account.name)
        && Objects.equals(accountNo, account.accountNo)
        && Objects.equals(iban, account.iban)
        && Objects.equals(openDate, account.openDate)
        && accountType == account.accountType
        && accountStatus == account.accountStatus
        && Objects.equals(branch, account.branch)
        && Objects.equals(customer, account.customer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        super.hashCode(),
        id,
        name,
        accountNo,
        iban,
        openDate,
        accountType,
        accountStatus,
        availableAmount,
        totalAmount,
        branch,
        customer);
  }
}
