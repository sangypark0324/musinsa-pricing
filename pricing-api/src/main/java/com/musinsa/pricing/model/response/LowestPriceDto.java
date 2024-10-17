package com.musinsa.pricing.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;


public class LowestPriceDto {
    private String categoryName;
    private String brandName;
    private BigDecimal lowestPrice;

    public LowestPriceDto(String categoryName, String brandName, BigDecimal lowestPrice) {
        this.categoryName = categoryName;
        this.brandName = brandName;
        this.lowestPrice = lowestPrice;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getBrandName() {
        return brandName;
    }

    public BigDecimal getLowestPrice() {
        return lowestPrice;
    }
}
