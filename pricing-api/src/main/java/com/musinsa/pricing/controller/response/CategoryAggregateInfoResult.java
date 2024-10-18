package com.musinsa.pricing.controller.response;

import com.musinsa.pricing.controller.response.dto.ProductDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "카테고리별 최저가/최고가 상품 결과")
public class CategoryAggregateInfoResult {
    @Schema(description = "카테고리명")
    private String categoryName;
    @Schema(description = "최저가")
    private List<ProductDto> lowestPriceProducts;
    @Schema(description = "최고가")
    private List<ProductDto> highestPriceProducts;

    public static CategoryAggregateInfoResult buildResult(String categoryName,List<ProductDto> lowestPriceProducts, List<ProductDto>highestPriceProducts) {
        return new CategoryAggregateInfoResult(categoryName,lowestPriceProducts,highestPriceProducts);
    }
}
