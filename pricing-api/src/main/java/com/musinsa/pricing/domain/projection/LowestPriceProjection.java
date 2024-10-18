package com.musinsa.pricing.domain.projection;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

public interface LowestPriceProjection {
    Long getCategoryId();
    String getCategoryName();
    Long getBrandId();
    String getBrandName();
    Long getProductId();
    BigDecimal getLowestPrice();
}
