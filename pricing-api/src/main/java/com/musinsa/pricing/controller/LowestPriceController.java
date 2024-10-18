package com.musinsa.pricing.controller;

import com.musinsa.pricing.model.response.CategoryAggregateInfoResult;
import com.musinsa.pricing.model.response.LowestBrandResult;
import com.musinsa.pricing.model.response.LowestPricePerCategoryResult;
import com.musinsa.pricing.service.LowestPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LowestPriceController {

    public LowestPriceController(LowestPriceService lowestPriceService) {
        this.lowestPriceService = lowestPriceService;
    }

    private final LowestPriceService lowestPriceService;

    @GetMapping("/lowest-price/categories")
    public ResponseEntity<LowestPricePerCategoryResult> getLowestPricePerCategory() {
        LowestPricePerCategoryResult result =  lowestPriceService.findLowestPriceProductByAllCategories();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/lowest-price/brand")
    public ResponseEntity<LowestBrandResult> getLowestPricePerBrand() {
        LowestBrandResult result =  lowestPriceService.getBrandWithLowestTotalPrice();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/lowest-brand/category")
    public ResponseEntity<CategoryAggregateInfoResult> getLowestAndHighestPriceBrandByCategory(@Param("categoryName")String categoryName) {
        CategoryAggregateInfoResult result =  lowestPriceService.getLowestAndHighestPriceInfoByCategoryName(categoryName);
        return ResponseEntity.ok(result);
    }
}
