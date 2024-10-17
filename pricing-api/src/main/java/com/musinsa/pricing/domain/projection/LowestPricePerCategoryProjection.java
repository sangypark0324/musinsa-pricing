package com.musinsa.pricing.domain.projection;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class LowestPricePerCategoryProjection {
    private Long categoryId;
    private String categoryName;
    private Long brandId;
    private String brandName;
    private Long productId;
    private BigDecimal lowestPrice;
}
