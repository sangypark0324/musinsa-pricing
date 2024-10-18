package com.musinsa.pricing.controller.response;

import com.musinsa.pricing.controller.response.dto.BrandDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "브랜드 수정 결과")
public class BrandCommandResult {

    @Schema(description = "수정결과")
    private String message;

    public static BrandCommandResult buildResult(BrandDto brandDto) {
        if(brandDto.getId() != 0) {
            return new BrandCommandResult("실패");
        }
        return new BrandCommandResult("성공");
    }
}
