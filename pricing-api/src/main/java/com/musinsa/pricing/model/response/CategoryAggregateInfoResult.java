package com.musinsa.pricing.model.response;

import com.musinsa.pricing.model.response.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryAggregateInfoResult {
    private String categoryName;
    private List<ProductDto> lowestPriceProducts;
    private List<ProductDto> highestPriceProducts;

    public static CategoryAggregateInfoResult buildResult(String categoryName,List<ProductDto> lowestPriceProducts, List<ProductDto>highestPriceProducts) {
        return new CategoryAggregateInfoResult(categoryName,lowestPriceProducts,highestPriceProducts);
    }
}
