package com.musinsa.pricing.controller;

import com.musinsa.pricing.controller.response.*;
import com.musinsa.pricing.service.LowestPriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LowestPriceController {


    private final LowestPriceService lowestPriceService;


    @Operation(summary = "카테고리별 최저가 브랜드 조회 API", description = "카테고리별 최저가 조회 ", tags = {"lowest-price"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = BrandCommandResult.class))),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))}
    )
    @GetMapping("/lowest-price/categories")
    public ResponseEntity<LowestPricePerCategoryResult> getLowestPricePerCategory() {
        LowestPricePerCategoryResult result =  lowestPriceService.findLowestPriceProductByAllCategories();
        return ResponseEntity.ok(result);
    }


    @Operation(summary = "최저가 브랜드 조회 API", description = "최저가 브랜드 조회 ", tags = {"lowest-price"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = BrandCommandResult.class))),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))}
    )
    @GetMapping("/lowest-price/brand")
    public ResponseEntity<LowestBrandResult> getLowestPricePerBrand() {
        LowestBrandResult result =  lowestPriceService.getBrandWithLowestTotalPrice();
        return ResponseEntity.ok(result);
    }


    @Operation(summary = "카테고리의 최저가 브랜드 조회 API", description = "카테고리의 최저가 브랜드 조회 ", tags = {"lowest-price"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = BrandCommandResult.class))),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))}
    )
    @GetMapping("/lowest-brand/category")
    public ResponseEntity<CategoryAggregateInfoResult> getLowestAndHighestPriceBrandByCategory(@Param("categoryName")String categoryName) {
        CategoryAggregateInfoResult result =  lowestPriceService.getLowestAndHighestPriceInfoByCategoryName(categoryName);
        return ResponseEntity.ok(result);
    }
}
