package com.musinsa.pricing.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseEntity {

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdDate;

    @Column(updatable = false, nullable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(nullable = true)
    private LocalDateTime modifiedDate;

    @Column(nullable = true)
    private String modifiedBy;

    public void updateCreatedDateAndCreatedBy() {
        createdBy = "ADMIN";
        createdDate = LocalDateTime.now();
    }

    public void updateModifiedDateAndModifiedBy() {
        modifiedBy = "ADMIN";
        modifiedDate = LocalDateTime.now();
    }
}
