package com.musinsa.pricing.exception;

import lombok.Getter;

@Getter
public enum ErrorType {
    NO_RESOURCE("데이터가 존재하지 않습니다."),
    UNKNOWN("알 수 없는 에러입니다.");
    ErrorType(String description) {
        this.description = description;
    }

    private final String description;
}
