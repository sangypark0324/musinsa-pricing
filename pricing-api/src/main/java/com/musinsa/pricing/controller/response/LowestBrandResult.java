package com.musinsa.pricing.controller.response;

import com.musinsa.pricing.controller.response.dto.LowestPriceBrandInfoDto;
import com.musinsa.pricing.controller.response.dto.LowestPriceCategoryPerBrandDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "최저가 브랜드 결과")
public class LowestBrandResult {

    @Schema(description = "최저가 브랜드 결과")
    private LowestPriceBrandInfoDto lowestPriceBrandInfo;

    public static LowestBrandResult buildResult(String brandName, List<LowestPriceCategoryPerBrandDto> categories,BigDecimal totalPrice) {
        LowestPriceBrandInfoDto lowestPriceBrandInfoDto = new LowestPriceBrandInfoDto(brandName,categories,totalPrice);
        return new LowestBrandResult(lowestPriceBrandInfoDto);
    }
}
