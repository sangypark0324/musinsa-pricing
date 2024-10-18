package com.musinsa.pricing.controller.response.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Schema(description = "최저가 브랜드")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LowestPriceDto {
    @Schema(description = "카테고리명")
    private String categoryName;
    @Schema(description = "브랜드명")
    private String brandName;
    @Schema(description = "최저가")
    private BigDecimal lowestPrice;

}
