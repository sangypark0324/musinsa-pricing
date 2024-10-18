package com.musinsa.pricing.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "brand_category")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BrandCategory extends BaseEntity{

    public BrandCategory(Brand brand, Category category) {
        this.brand = brand;
        this.category = category;
    }

    @EmbeddedId
    private BrandCategoryId id = new BrandCategoryId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("brandId")
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("categoryId")
    @JoinColumn(name = "category_id")
    private Category category;

}

