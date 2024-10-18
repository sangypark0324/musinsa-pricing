package com.musinsa.pricing.service;

import com.musinsa.pricing.exception.BusinessRuleException;
import com.musinsa.pricing.exception.ErrorType;
import com.musinsa.pricing.domain.projection.BrandTotalLowestPriceProjection;
import com.musinsa.pricing.domain.projection.CategoryAggregateInfoProjection;
import com.musinsa.pricing.domain.projection.LowestPriceProjection;
import com.musinsa.pricing.model.response.CategoryAggregateInfoResult;
import com.musinsa.pricing.model.response.LowestBrandResult;
import com.musinsa.pricing.model.response.dto.LowestPriceCategoryPerBrandDto;
import com.musinsa.pricing.model.response.dto.LowestPriceDto;
import com.musinsa.pricing.model.response.LowestPricePerCategoryResult;
import com.musinsa.pricing.model.response.dto.ProductDto;
import com.musinsa.pricing.repository.BrandRepository;
import com.musinsa.pricing.repository.CategoryAggregateInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LowestPriceService {
    private final CategoryAggregateInfoRepository categoryAggregateInfoRepository;
    private final BrandRepository brandRepository;

    public LowestPricePerCategoryResult findLowestPriceProductByAllCategories() {
        List<LowestPriceProjection> lowestPriceProjections =
                categoryAggregateInfoRepository.findLowestPriceByCategory();
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

    public CategoryAggregateInfoResult getLowestAndHighestPriceInfoByCategoryName(String categoryName) {
        Optional<CategoryAggregateInfoProjection> categoryAggregateInfoOptional =  categoryAggregateInfoRepository.findCategoryAggregateInfoByCategoryName(categoryName);
        CategoryAggregateInfoProjection categoryAggregateInfo =
                categoryAggregateInfoOptional.orElseThrow(() -> new BusinessRuleException("존재하지 않는 카테고리 입니다.", ErrorType.NO_RESOURCE));
        ProductDto highestePriceProductDto = new ProductDto(categoryAggregateInfo.getHighestPriceBrand(),categoryAggregateInfo.getHighestPrice());
        ProductDto lowestPriceProductDto = new ProductDto(categoryAggregateInfo.getLowestPriceBrand(),categoryAggregateInfo.getLowestPrice());
        return CategoryAggregateInfoResult.buildResult(
                categoryAggregateInfo.getCategoryName(),
                List.of(highestePriceProductDto),
                List.of(lowestPriceProductDto)
        );
    }
}
