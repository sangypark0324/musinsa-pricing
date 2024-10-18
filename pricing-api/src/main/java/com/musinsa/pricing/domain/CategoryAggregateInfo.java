package com.musinsa.pricing.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "category_aggregate_info")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryAggregateInfo extends BaseEntity {

    @Id
    @Column(name = "category_id")
    private Long categoryId;

    @OneToOne
    @JoinColumn(name = "lowest_price_product_id")
    private Product lowestPriceProduct;

    @OneToOne
    @JoinColumn(name = "highest_price_product_id")
    private Product highestPriceProduct;

    @Column(nullable = false, name = "lowest_price")
    private BigDecimal lowestPrice;

    @Column(nullable = false, name = "highest_price")
    private BigDecimal highestPrice;

}

