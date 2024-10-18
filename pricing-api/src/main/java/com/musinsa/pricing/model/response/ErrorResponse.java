package com.musinsa.pricing.model.response;


import com.musinsa.pricing.exception.ErrorType;

public record ErrorResponse(
        String errorMessage,
        ErrorType errorType) {
}