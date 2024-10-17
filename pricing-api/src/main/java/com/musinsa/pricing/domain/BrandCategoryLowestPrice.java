package com.musinsa.pricing.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "brand_category_lowest_price")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BrandCategoryLowestPrice {

    @EmbeddedId
    private BrandCategoryId id;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @MapsId("brandId")
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    @MapsId("categoryId")
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(nullable = false)
    private BigDecimal lowestPrice;

}
