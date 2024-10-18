package com.musinsa.pricing.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "상품 등록/수정 결과")
public class SetProductResult {
    @Schema(description = "결과", example = "상품등록 성공")
    private String message;
    @Schema(description = "상품아이디", example = "1")
    private long productId;
}
