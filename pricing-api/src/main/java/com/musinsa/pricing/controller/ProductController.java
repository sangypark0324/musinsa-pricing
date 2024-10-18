package com.musinsa.pricing.controller;

import com.musinsa.pricing.controller.request.SetProductRequest;
import com.musinsa.pricing.controller.response.BrandCommandResult;
import com.musinsa.pricing.controller.response.ErrorResponse;
import com.musinsa.pricing.controller.response.SetProductResult;
import com.musinsa.pricing.controller.response.dto.BrandDto;
import com.musinsa.pricing.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/api/products")
@RequiredArgsConstructor
@Tag(name = "product", description = "product api")
public class ProductController {
    private final ProductService productService;

    @Operation(summary = "상품 등록 API", description = "상품 등록 ", tags = {"product"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = BrandCommandResult.class))),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))}
    )
    @PostMapping
    public ResponseEntity<SetProductResult> addProduct(@RequestBody SetProductRequest setProductRequest) {
        long productId = productService.addProduct(setProductRequest);
        return ResponseEntity.ok(new SetProductResult("상품 등록 완료",productId));
    }

    @Operation(summary = "상품 수정 API", description = "상품 수정 ", tags = {"product"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = BrandCommandResult.class))),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))}
    )
    @PutMapping("/{id}")
    public ResponseEntity<SetProductResult> updateProduct(@PathVariable long id, @RequestBody SetProductRequest setProductRequest) {
        long productId = productService.updateProduct(id,setProductRequest);
        return ResponseEntity.ok(new SetProductResult("상품 수정 성공",productId));
    }

    @Operation(summary = "상품 삭제 API", description = "상품 삭제 ", tags = {"product"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = BrandCommandResult.class))),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))}
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<SetProductResult> deleteProduct(@PathVariable long id) {
        long productId = productService.deleteProduct(id);
        return ResponseEntity.ok(new SetProductResult("상품 삭제 성공",productId));
    }
}
