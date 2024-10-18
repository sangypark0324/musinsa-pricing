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

    private final CategoryService categoryService;
    private final BrandRepository brandRepository;

    @Transactional
    public long addBrand(BrandDto brandDto) {
        List<Category> categories = categoryService.getAllCategories();
        Brand brand = new Brand(brandDto.getName(),categories);
        Brand savedBrand = brandRepository.save(brand);
        return savedBrand.getId();
    }

    @Transactional
    public long updateBrand(long id, BrandDto brandDto) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("존재하지 않는 브랜드 입니다.",ErrorType.NO_RESOURCE));
        brand.updateBrandName(brandDto.getName());
        Brand updatedBrand = brandRepository.save(brand);
        return id;
    }

    @Transactional
    public long deleteBrand(long brandId) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new BusinessRuleException("존재하지 않는 브랜드 입니다.", ErrorType.NO_RESOURCE));
        brandRepository.delete(brand);
        return brandId;
    }

    @Transactional(readOnly = true)
    public Brand getBrand(long brandId) {
        return brandRepository.findById(brandId)
                .orElseThrow(() -> new BusinessRuleException("존재하지 않는 브랜드 입니다.", ErrorType.NO_RESOURCE));
    }

}
