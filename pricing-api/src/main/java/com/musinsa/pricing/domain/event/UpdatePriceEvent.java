package com.musinsa.pricing.domain.event;

import java.math.BigDecimal;


public record UpdatePriceEvent (long productId, long categoryId, BigDecimal price){
}
