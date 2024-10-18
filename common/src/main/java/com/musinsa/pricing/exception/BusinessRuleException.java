package com.musinsa.pricing.exception;

import lombok.Getter;
@Getter
public class BusinessRuleException extends RuntimeException {
    private final String message;
    private final ErrorType errorType;
    public BusinessRuleException(String message, ErrorType errorType) {
        this.message = message;
        this.errorType = errorType;
    }
}
