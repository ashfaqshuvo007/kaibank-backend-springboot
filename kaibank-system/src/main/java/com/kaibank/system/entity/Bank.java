package com.kaibank.system.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * The Bank entity class is used to define bank attributes and methods.
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
    name = "BANK",
    indexes = {@Index(name = "bank_id_pk_index", columnList = "ID")})
public class Bank implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID", unique = true, nullable = false)
  private Long id;

  @Column(name = "NAME", length = 100, nullable = false)
  private String name;

  @Column(name = "ADDRESS", length = 1000, nullable = false)
  private String address;

  @Column(name = "PHYSICAL_AMOUNT", nullable = false)
  private Double physicalAmount;

  @Column(name = "VIRTUAL_AMOUNT", nullable = false)
  private Double virtualAmount;

  @OneToMany(
      fetch = FetchType.LAZY,
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      mappedBy = "bank")
  private Set<Branch> branches;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Bank bank = (Bank) o;
    return Objects.equals(id, bank.id)
        && Objects.equals(name, bank.name)
        && Objects.equals(address, bank.address)
        && Objects.equals(physicalAmount, bank.physicalAmount)
        && Objects.equals(virtualAmount, bank.virtualAmount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, address, physicalAmount, virtualAmount);
  }
}
