package com.musinsa.pricing.controller.response;


import com.musinsa.pricing.exception.ErrorType;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "에러응답")
public record ErrorResponse(
        @Schema(description = "에러 메세지", example = "카테고리가 존재하지 않습니다.")
        String reason,
        @Schema(description = "에러 타입", example = "NO_RESOURCE")
        ErrorType errorType) {
}