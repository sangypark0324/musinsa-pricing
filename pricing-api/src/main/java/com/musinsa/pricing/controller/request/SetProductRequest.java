package com.musinsa.pricing.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SetProductRequest {
    private String name;
    private BigDecimal price;
    private Long brandId;
    private Long categoryId;
    private String status;
}
