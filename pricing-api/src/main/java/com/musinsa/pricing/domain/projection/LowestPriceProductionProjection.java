package com.musinsa.pricing.domain.projection;

import java.math.BigDecimal;

public interface LowestPriceProductionProjection {
    Long getCategoryId();
    String getCategoryName();
    BigDecimal getLowestPrice();
}
