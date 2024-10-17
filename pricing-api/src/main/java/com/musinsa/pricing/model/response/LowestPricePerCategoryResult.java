package com.musinsa.pricing.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LowestPricePerCategoryResult {
    private List<LowestPriceDto> lowestPricePerCategory;
    private BigDecimal totalPrice;

    public static LowestPricePerCategoryResult buildResult(List<LowestPriceDto> lowestPricePerCategory, BigDecimal totalPrice) {
       return new LowestPricePerCategoryResult(lowestPricePerCategory,totalPrice);
    }

}
