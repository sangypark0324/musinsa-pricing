package com.musinsa.pricing.service;

import com.musinsa.pricing.domain.projection.BrandTotalLowestPriceProjection;
import com.musinsa.pricing.domain.projection.LowestPriceProductionProjection;
import com.musinsa.pricing.domain.projection.LowestPriceProjection;
import com.musinsa.pricing.model.response.LowestBrandResult;
import com.musinsa.pricing.model.response.dto.LowestPriceCategoryPerBrandDto;
import com.musinsa.pricing.model.response.dto.LowestPriceDto;
import com.musinsa.pricing.model.response.LowestPricePerCategoryResult;
import com.musinsa.pricing.repository.BrandRepository;
import com.musinsa.pricing.repository.CategoryLowestPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LowestPriceService {
    private final CategoryLowestPriceRepository categoryLowestPriceRepository;
    private final BrandRepository brandRepository;

    public LowestPricePerCategoryResult findLowestPriceProductByAllCategories() {
        List<LowestPriceProjection> lowestPriceProjections =
                categoryLowestPriceRepository.findLowestPriceByCategory();
        List<LowestPriceDto> lowestPriceDtos =
                lowestPriceProjections.stream().map(it -> new LowestPriceDto(it.getCategoryName(),it.getBrandName(),it.getLowestPrice())).toList();
        BigDecimal totalPrice = lowestPriceDtos.stream()
                .map(LowestPriceDto::getLowestPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return LowestPricePerCategoryResult.buildResult(lowestPriceDtos,totalPrice);
    }

    public LowestBrandResult getBrandWithLowestTotalPrice() {
        BrandTotalLowestPriceProjection brandTotalLowestPriceProjection =
                brandRepository.findBrandWithLowestTotalPrice();
        List<LowestPriceProjection> lowestPriceProductions =
                brandRepository.findLowestPriceProductsByBrandId(brandTotalLowestPriceProjection.getBrandId());
        List<LowestPriceCategoryPerBrandDto> lowestPriceCategoryPerBrandDtos= lowestPriceProductions
                .stream()
                .map(it -> new LowestPriceCategoryPerBrandDto(it.getCategoryName(),it.getLowestPrice()))
                .toList();
        return LowestBrandResult.buildResult(
                brandTotalLowestPriceProjection.getBrandName(),
                lowestPriceCategoryPerBrandDtos,
                brandTotalLowestPriceProjection.getTotalLowestPrice()
        );
    }
}
