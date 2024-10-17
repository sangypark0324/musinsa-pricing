package com.musinsa.pricing.model.response.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LowestPriceCategoryPerBrandDto {
    private String categoryName;
    private BigDecimal price;
}
