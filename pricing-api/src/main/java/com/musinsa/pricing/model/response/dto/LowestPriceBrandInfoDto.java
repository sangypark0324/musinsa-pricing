package com.musinsa.pricing.model.response.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LowestPriceBrandInfoDto {
    private String brandName;
    private List<LowestPriceCategoryPerBrandDto> categories;
    private BigDecimal totalPrice;
}
