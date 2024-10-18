package com.musinsa.pricing.controller.response.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "브랜드멸 최저가")
public class LowestPriceCategoryPerBrandDto {
    @Schema(description = "카테고리명")
    private String categoryName;
    @Schema(description = "가격")
    private BigDecimal price;
}
