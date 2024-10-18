package com.musinsa.pricing.controller;

import com.musinsa.pricing.controller.response.BrandCommandResult;
import com.musinsa.pricing.controller.response.ErrorResponse;
import com.musinsa.pricing.controller.response.dto.BrandDto;
import com.musinsa.pricing.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
@Tag(name = "brand", description = "brand api")
public class BrandController {

    private final BrandService brandService;

    @Operation(summary = "브랜드 등록 API", description = "브랜드 등록 ", tags = {"brand"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = BrandCommandResult.class))),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))}
    )
    @PostMapping
    public ResponseEntity<BrandCommandResult> addBrand(@RequestBody BrandDto brandDto) {
        BrandDto createdBrand = brandService.addBrand(brandDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(BrandCommandResult.buildResult(brandDto));
    }


    @Operation(summary = "브랜드 수정 API", description = "브랜드 수정 ", tags = {"brand"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = BrandCommandResult.class))),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))}
    )
    @PutMapping("/{id}")
    public ResponseEntity<BrandCommandResult> updateBrand(@PathVariable long id,  @RequestBody BrandDto brandDto) {
        BrandDto updatedBrand = brandService.updateBrand(id, brandDto);
        return ResponseEntity.ok(BrandCommandResult.buildResult(brandDto));
    }


    @Operation(summary = "브랜드 삭제 API", description = "브랜드 삭제 ", tags = {"brand"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = BrandCommandResult.class))),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))}
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<BrandCommandResult> deleteBrand(@PathVariable long id) {
        brandService.deleteBrand(new BrandDto(id));
        return ResponseEntity.ok().build();
    }
}
