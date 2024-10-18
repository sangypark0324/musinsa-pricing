package com.musinsa.pricing.controller.response.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "상품정보")
public class ProductDto {
    @Schema(description = "브랜드명")
    private String brandName;
    @Schema(description = "가격")
    private BigDecimal price;
}
