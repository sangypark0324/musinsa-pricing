package com.musinsa.pricing.controller;

import com.musinsa.pricing.model.response.LowestPricePerCategoryResult;
import com.musinsa.pricing.service.LowestPriceService;
import lombok.RequiredArgsConstructor;
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
}
