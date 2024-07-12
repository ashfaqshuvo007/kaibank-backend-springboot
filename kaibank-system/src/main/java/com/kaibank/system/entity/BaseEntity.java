package com.kaibank.system.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * The BaseEntity class is used to define common attributes and methods.
 *
 * @author Pabasara
 * @version 1.0
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
public class BaseEntity implements Serializable {

  /** serialVersionUID */
  private static final long serialVersionUID = 1L;

  /** created time */
  @NotNull
  @CreatedDate
  @Column(name = "CREATED_TIME", nullable = false, length = 25)
  private LocalDateTime createdTime = LocalDateTime.now();

  /** modified time */
  @LastModifiedDate
  @Column(name = "MODIFIED_TIME", length = 25)
  private LocalDateTime modifiedTime;

  /** created by user */
  @NotNull
  @CreatedBy
  @Column(name = "CREATED_BY_USER", nullable = false, length = 25)
  private String createdByUser = "SYSTEM";

  /** modified by user */
  @LastModifiedBy
  @Column(name = "MODIFIED_BY_USER", length = 25)
  private String modifiedByUser;

  /** version id */
  @Version
  @Column(name = "VERSION_ID")
  private long versionId = 0;

  /**
   * Gets a String object representation of the BaseEntity Object.
   *
   * @return String object representation of the BaseEntity.
   * @since 1.0
   */
  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
  }
}
