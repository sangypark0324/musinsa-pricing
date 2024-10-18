package com.musinsa.pricing.domain.projection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


public interface CategoryAggregateInfoProjection {
    String getCategoryName();

    BigDecimal getLowestPrice();

    String getLowestPriceBrand();

    BigDecimal getHighestPrice();

    String getHighestPriceBrand();
}
