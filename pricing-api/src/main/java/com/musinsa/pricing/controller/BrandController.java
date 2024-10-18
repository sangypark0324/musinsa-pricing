package com.musinsa.pricing.controller;

import com.musinsa.pricing.model.response.BrandCommandResult;
import com.musinsa.pricing.model.response.dto.BrandDto;
import com.musinsa.pricing.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @PostMapping
    public ResponseEntity<BrandCommandResult> addBrand(@RequestBody BrandDto brandDto) {
        BrandDto createdBrand = brandService.addBrand(brandDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(BrandCommandResult.buildResult(brandDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandCommandResult> updateBrand(@PathVariable long id,  @RequestBody BrandDto brandDto) {
        BrandDto updatedBrand = brandService.updateBrand(id, brandDto);
        return ResponseEntity.ok(BrandCommandResult.buildResult(brandDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BrandCommandResult> deleteBrand(@PathVariable long id) {
        brandService.deleteBrand(new BrandDto(id));
        return ResponseEntity.ok().build();
    }
}
