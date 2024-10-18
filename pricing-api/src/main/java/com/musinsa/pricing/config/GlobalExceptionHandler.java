package com.musinsa.pricing.config;

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
    public ResponseEntity<ErrorResponse> handleBusinessRuleException(BusinessRuleException e) {
        log.error("BusinessRuleExcepion occurred. message={}, className={}",e.getMessage(),e.getClass().getName());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage(),e.getErrorType()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("Exception occurred. message={}, className={}", e.getMessage(), e.getClass().getName());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(ErrorType.UNKNOWN.getDescription(), ErrorType.UNKNOWN));
    }
}
