package com.musinsa.pricing.service;

import com.musinsa.pricing.controller.request.SetProductRequest;
import com.musinsa.pricing.domain.*;
import com.musinsa.pricing.domain.event.UpdatePriceEvent;
import com.musinsa.pricing.exception.BusinessRuleException;
import com.musinsa.pricing.exception.ErrorType;
import com.musinsa.pricing.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final BrandService brandService;
    private final CategoryService categoryService;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public long addProduct(SetProductRequest setProductRequest) {
        Brand brand = brandService.getBrand(setProductRequest.getBrandId());
        Category category = categoryService.getCategory(setProductRequest.getCategoryId());
        ProductStatus productStatus = ProductStatus.valueOf(setProductRequest.getStatus().toUpperCase());
        Product product = new Product(setProductRequest.getName(), setProductRequest.getPrice(),brand,category,productStatus);
        Product savedProduct = productRepository.save(product);
        publishEvent(savedProduct.getId(),savedProduct.getCategory().getId(),savedProduct.getPrice());
        return savedProduct.getId();
    }

    @Transactional
    public long updateProduct(long productId,SetProductRequest setProductRequest) {
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new BusinessRuleException("존재하지 않는 상품입니다.", ErrorType.NO_RESOURCE));
        Brand brand = brandService.getBrand(setProductRequest.getBrandId());
        Category category = categoryService.getCategory(setProductRequest.getCategoryId());
        ProductStatus productStatus = ProductStatus.valueOf(setProductRequest.getStatus().toUpperCase());
        product.updateProduct(setProductRequest.getName(), setProductRequest.getPrice(),brand,category,productStatus);
        Product savedProduct = productRepository.save(product);
        publishEvent(savedProduct.getId(),savedProduct.getCategory().getId(),savedProduct.getPrice());
        return savedProduct.getId();
    }


    @Transactional
    public long deleteProduct(long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new BusinessRuleException("존재하지 않는 상품입니다.", ErrorType.NO_RESOURCE));
        productRepository.delete(product);
        return productId;
    }

    private void publishEvent(long productId,long categoryId, BigDecimal price) {
        eventPublisher.publishEvent(new UpdatePriceEvent(productId,categoryId,price));
    }
}

