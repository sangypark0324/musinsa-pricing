package com.musinsa.pricing.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "category_lowest_price")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryLowestPrice extends BaseEntity{

    @Id
    @Column(name = "category_id")
    private Long categoryId;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false,name = "lowest_price")
    private BigDecimal lowestPrice;

    // Getters and Setters
}
