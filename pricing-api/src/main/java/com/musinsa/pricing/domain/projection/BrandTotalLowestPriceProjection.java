package com.musinsa.pricing.domain.projection;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;


public interface BrandTotalLowestPriceProjection {
    Long getBrandId();
    String getBrandName();
    BigDecimal getTotalLowestPrice();
}
