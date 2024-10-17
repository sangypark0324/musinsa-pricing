package com.musinsa.pricing.service;

import com.musinsa.pricing.domain.projection.LowestPricePerCategoryProjection;
import com.musinsa.pricing.model.response.LowestPriceDto;
import com.musinsa.pricing.model.response.LowestPricePerCategoryResult;
import com.musinsa.pricing.repository.CategoryLowestPriceRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LowestPriceService {
    private final CategoryLowestPriceRepository categoryLowestPriceRepository;

    public LowestPricePerCategoryResult findLowestPriceProductByAllCategories() {
        List<LowestPricePerCategoryProjection> lowestPricePerCategoryProjections =
                categoryLowestPriceRepository.findLowestPriceByCategory();
        List<LowestPriceDto> lowestPriceDtos =
                lowestPricePerCategoryProjections.stream().map(it -> new LowestPriceDto(it.getCategoryName(),it.getBrandName(),it.getLowestPrice())).toList();
        BigDecimal totalPrice = lowestPriceDtos.stream()
                .map(LowestPriceDto::getLowestPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return LowestPricePerCategoryResult.buildResult(lowestPriceDtos,totalPrice);
    }
}
