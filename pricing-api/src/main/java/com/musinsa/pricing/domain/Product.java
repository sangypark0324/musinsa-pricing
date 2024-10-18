package com.musinsa.pricing.domain;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;

@Entity
@Table(name = "product",indexes = @Index(name = "IDX_PRODUCT_01",columnList = "price,id"))
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE product SET deleted = true WHERE id = ?")
@SQLRestriction( "deleted = false")
public class Product extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)  // 다대일 관계
    @JoinColumn(name = "category_id")  // 외래키
    private Category category;  // 카테고리

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductStatus status;

    @Column
    @ColumnDefault(value = "0")
    private boolean deleted = Boolean.FALSE; // 삭제 여부 기본값 false

    public Product (String name,BigDecimal price, Brand brand, Category category, ProductStatus status) {
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.category = category;
        this.status = status;
        this.updateCreatedDateAndCreatedBy();
    }

    public void updateProduct(String name,BigDecimal price, Brand brand, Category category, ProductStatus status) {
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.category = category;
        this.status = status;
        this.updateCreatedDateAndCreatedBy();
    }
}

