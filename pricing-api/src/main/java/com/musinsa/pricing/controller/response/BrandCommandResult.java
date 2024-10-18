package com.musinsa.pricing.controller.response;

import com.musinsa.pricing.controller.response.dto.BrandDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "브랜드 수정 결과")
public class BrandCommandResult {

    @Schema(description = "수정결과")
    private String message;

    @Schema(description = "브랜드아이디")
    private long brandId;

    public static BrandCommandResult buildResult(long brandId) {
        if(brandId != 0) {
            return new BrandCommandResult("성공",brandId);
        }
        return new BrandCommandResult("실패",brandId);
    }
}
