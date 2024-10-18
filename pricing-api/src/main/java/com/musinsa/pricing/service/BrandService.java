package com.musinsa.pricing.service;

import com.musinsa.pricing.exception.BusinessRuleException;
import com.musinsa.pricing.exception.ErrorType;
import com.musinsa.pricing.domain.Brand;
import com.musinsa.pricing.domain.Category;
import com.musinsa.pricing.controller.response.dto.BrandDto;
import com.musinsa.pricing.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {

    private CategoryService categoryService;
    private BrandRepository brandRepository;

    @Transactional
    public BrandDto addBrand(BrandDto brandDto) {
        List<Category> categories = categoryService.getAllCategories();
        Brand brand = new Brand(brandDto.getName(),categories);
        Brand savedBrand = brandRepository.save(brand);
        return convertToDto(savedBrand);
    }

    @Transactional
    public BrandDto updateBrand(long id, BrandDto brandDto) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("존재하지 않는 브랜드 입니다.",ErrorType.NO_RESOURCE));
        brand.updateBrandName(brandDto.getName());
        Brand updatedBrand = brandRepository.save(brand);
        return convertToDto(updatedBrand);
    }

    @Transactional
    public BrandDto deleteBrand(BrandDto brandDto) {
        Brand brand = brandRepository.findById(brandDto.getId())
                .orElseThrow(() -> new BusinessRuleException("존재하지 않는 브랜드 입니다.", ErrorType.NO_RESOURCE));
        brandRepository.delete(brand);
        return brandDto;
    }

    @Transactional(readOnly = true)
    public Brand getBrand(long brandId) {
        return brandRepository.findById(brandId)
                .orElseThrow(() -> new BusinessRuleException("존재하지 않는 브랜드 입니다.", ErrorType.NO_RESOURCE));
    }

    private BrandDto convertToDto(Brand brand) {
        return new BrandDto(brand.getId(), brand.getName());
    }
}
