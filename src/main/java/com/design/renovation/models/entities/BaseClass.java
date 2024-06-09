package com.design.renovation.models.entities;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseClass<T> {
  @CreatedBy
  protected T createdBy;

  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  protected Date createdDate;

  @LastModifiedBy
  protected T updatedBy;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  protected Date updatedDate;
}
