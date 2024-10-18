package com.musinsa.pricing.config;

import com.musinsa.pricing.controller.response.ApiResponseWrapper;
import com.musinsa.pricing.exception.BusinessRuleException;
import com.musinsa.pricing.exception.ErrorType;
import com.musinsa.pricing.controller.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ApiResponseWrapper<?>> handleBusinessRuleException(BusinessRuleException e) {
        log.error("BusinessRuleExcepion occurred. message={}, className={}",e.getMessage(),e.getClass().getName());
        ApiResponseWrapper<?> response = new ApiResponseWrapper<>().buildErrorResponse(new ErrorResponse(e.getMessage(), ErrorType.NO_RESOURCE));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseWrapper<?>> handleException(Exception e) {
        log.error("Exception occurred. message={}, className={}", e.getMessage(), e.getClass().getName());
        ApiResponseWrapper<?> response = new ApiResponseWrapper<>().buildErrorResponse(new ErrorResponse(ErrorType.UNKNOWN.getDescription(), ErrorType.UNKNOWN));
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
}
