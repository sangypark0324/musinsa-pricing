package com.musinsa.pricing.model.response;

import com.musinsa.pricing.model.response.dto.LowestPriceBrandInfoDto;
import com.musinsa.pricing.model.response.dto.LowestPriceCategoryPerBrandDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LowestBrandResult {
    private LowestPriceBrandInfoDto lowestPriceBrandInfo;

    public static LowestBrandResult buildResult(String brandName, List<LowestPriceCategoryPerBrandDto> categories,BigDecimal totalPrice) {
        LowestPriceBrandInfoDto lowestPriceBrandInfoDto = new LowestPriceBrandInfoDto(brandName,categories,totalPrice);
        return new LowestBrandResult(lowestPriceBrandInfoDto);
    }
}
