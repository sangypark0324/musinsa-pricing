package com.musinsa.pricing.model.response.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BrandDto {
    private long id;
    private String name;

    public BrandDto(long id) {
        this.id = id;
    }
}
