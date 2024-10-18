package com.musinsa.pricing.controller.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseWrapper<T> {
    private T data;
    private ErrorResponse error;
    private boolean success;

    public ApiResponseWrapper<T> buildErrorResponse(ErrorResponse error) {
        return new ApiResponseWrapper<T>(null,error,false);
    }

    public ApiResponseWrapper<T> buildSuccessResponse(T data) {
        return new ApiResponseWrapper<T>(data,null,true);
    }
}
