package com.kaibank.system.entity;

import com.kaibank.system.enums.TransactionStatus;
import com.kaibank.system.enums.TransactionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The Transaction entity class is used to define transaction attributes and methods.
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
    name = "TRANSACTION",
    indexes = {@Index(name = "transaction_id_pk_index", columnList = "ID")})
public class Transaction extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID", unique = true, nullable = false)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(name = "TRANSACTION_TYPE", nullable = false)
  private TransactionType transactionType;

  @Column(name = "AMOUNT", nullable = false)
  private double amount;

  @Column(name = "TRANSACTION_DATE", length = 25, nullable = false)
  private LocalDateTime transactionDate;

  @Enumerated(EnumType.STRING)
  @Column(name = "TRANSACTION_STATUS", nullable = false)
  private TransactionStatus transactionStatus;

  @Column(name = "MESSAGE", length = 1000, nullable = true)
  private String message;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "RECIPIENT_ID",referencedColumnName = "ID", nullable = true)
  private Account recipient;

  @Column(name = "PREV_TRANS_ID", nullable = true)
  private Long prevTransId;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(
      name = "ACCOUNT_ID",
      foreignKey = @ForeignKey(name = "fk_account_with_transaction"),
      referencedColumnName = "ID")
  private Account account;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(
      name = "CUSTOMER_ID",
      foreignKey = @ForeignKey(name = "fk_customer_with_transaction"),
      referencedColumnName = "ID")
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
    Transaction that = (Transaction) o;
    return Double.compare(that.amount, amount) == 0
        && Objects.equals(id, that.id)
        && transactionType == that.transactionType
        && Objects.equals(transactionDate, that.transactionDate)
        && transactionStatus == that.transactionStatus
        && Objects.equals(message, that.message)
        && Objects.equals(recipient, that.recipient)
        && Objects.equals(prevTransId, that.prevTransId)
        && Objects.equals(account, that.account)
        && Objects.equals(customer, that.customer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        super.hashCode(),
        id,
        transactionType,
        amount,
        transactionDate,
        transactionStatus,
        message,
        recipient,
        prevTransId,
        account,
        customer);
  }
}
