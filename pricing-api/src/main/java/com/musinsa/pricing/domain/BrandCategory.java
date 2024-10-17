package com.musinsa.pricing.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "brand_category")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BrandCategory extends BaseEntity{

    @EmbeddedId
    private BrandCategoryId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("brandId")
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("categoryId")
    @JoinColumn(name = "category_id")
    private Category category;

}

