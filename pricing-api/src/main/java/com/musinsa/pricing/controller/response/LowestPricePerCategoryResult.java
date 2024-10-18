package com.musinsa.pricing.controller.response;

import com.musinsa.pricing.controller.response.dto.LowestPriceDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Schema(description = "카테고리별 최저가 브랜드 결과")
public class LowestPricePerCategoryResult {
    @Schema(description = "카테고리별 최저가 브랜드 상품")
    private List<LowestPriceDto> lowestPricePerCategory;
    @Schema(description = "전체가격", example = "10000")
    private BigDecimal totalPrice;

    public static LowestPricePerCategoryResult buildResult(List<LowestPriceDto> lowestPricePerCategory, BigDecimal totalPrice) {
       return new LowestPricePerCategoryResult(lowestPricePerCategory,totalPrice);
    }

}
