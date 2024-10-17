package com.musinsa.pricing.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BrandCategoryId implements Serializable {

    @Column(name = "brand_id")
    private Long brandId;

    @Column(name = "category_id")
    private Long categoryId;
}