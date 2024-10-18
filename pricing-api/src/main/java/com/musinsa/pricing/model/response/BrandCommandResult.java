package com.musinsa.pricing.model.response;

import com.musinsa.pricing.model.response.dto.BrandDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class BrandCommandResult {
    private String message;

    public static BrandCommandResult buildResult(BrandDto brandDto) {
        if(brandDto.getId() != 0) {
            return new BrandCommandResult("실패");
        }
        return new BrandCommandResult("성공");
    }
}
