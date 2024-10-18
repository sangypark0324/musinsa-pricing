package com.musinsa.pricing.controller.response.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "브랜드")
public class BrandDto {
    @Schema(description = "브랜드 아이디")
    private long id;
    @Schema(description = "브랜드명")
    private String name;

    public BrandDto(long id) {
        this.id = id;
    }
}
