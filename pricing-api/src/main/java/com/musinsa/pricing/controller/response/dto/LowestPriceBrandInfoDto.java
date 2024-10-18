package com.musinsa.pricing.controller.response.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Schema(description = "최저가 브랜드 정보")
public class LowestPriceBrandInfoDto {
    @Schema(description = "브랜드명")
    private String brandName;
    @Schema(description = "브랜드의 카테고리별 최저가")
    private List<LowestPriceCategoryPerBrandDto> categories;
    @Schema(description = "브랜드의 카테고리별 최저가 상품 가격 합계")
    private BigDecimal totalPrice;
}
