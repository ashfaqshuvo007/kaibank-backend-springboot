package com.kaibank.system.entity;

import com.kaibank.system.enums.RoleType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * The Role entity class is used to define role attributes and methods.
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
    name = "ROLE",
    indexes = {@Index(name = "role_id_pk_index", columnList = "ID")})
public class Role extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID", unique = true, nullable = false)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(name = "ROLE", nullable = false)
  private RoleType roleType;

  @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
  private Set<Employee> employees;

  @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
  private Set<Customer> customers;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Role role = (Role) o;
    return Objects.equals(id, role.id)
        && roleType == role.roleType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), id, roleType);
  }
}
