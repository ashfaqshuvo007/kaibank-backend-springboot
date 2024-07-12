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
 * The Branch entity class is used to define branch attributes and methods.
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
    name = "BRANCH",
    indexes = {@Index(name = "branch_id_pk_index", columnList = "ID")})
public class Branch extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID", unique = true, nullable = false)
  private Long id;

  @Column(name = "NAME", length = 100, nullable = false)
  private String name;

  @Column(name = "ADDRESS", length = 1000, nullable = false)
  private String address;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(
      name = "BANK_ID",
      foreignKey = @ForeignKey(name = "fk_bank_with_branch"),
      referencedColumnName = "ID")
  private Bank bank;

  @OneToMany(
      fetch = FetchType.LAZY,
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      mappedBy = "branch")
  private Set<Employee> employees;

  @OneToMany(
      fetch = FetchType.LAZY,
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      mappedBy = "branch")
  private Set<Account> accounts;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Branch branch = (Branch) o;
    return Objects.equals(id, branch.id)
        && Objects.equals(name, branch.name)
        && Objects.equals(address, branch.address)
        && Objects.equals(bank, branch.bank);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), id, name, address, bank);
  }
}
