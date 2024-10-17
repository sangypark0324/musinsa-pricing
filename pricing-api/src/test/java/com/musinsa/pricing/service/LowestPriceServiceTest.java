package com.musinsa.pricing.service;

import com.musinsa.pricing.domain.projection.LowestPriceProjection;
import com.musinsa.pricing.model.response.LowestPricePerCategoryResult;
import com.musinsa.pricing.repository.CategoryLowestPriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LowestPriceServiceTest {
    @Mock
    private CategoryLowestPriceRepository categoryLowestPriceRepository;

    @InjectMocks
    private LowestPriceService lowestPriceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void 카테고리별_최저가_브랜드를_조회하면_각_카테고리의_최저가_브랜드상품의_총액을_구한다() {
        // given
        LowestPriceProjection projection1 = mock(LowestPriceProjection.class);
        when(projection1.getCategoryName()).thenReturn("상의");
        when(projection1.getBrandName()).thenReturn("C");
        when(projection1.getLowestPrice()).thenReturn(new BigDecimal("10000"));

        LowestPriceProjection projection2 = mock(LowestPriceProjection.class);
        when(projection2.getCategoryName()).thenReturn("아우터");
        when(projection2.getBrandName()).thenReturn("E");
        when(projection2.getLowestPrice()).thenReturn(new BigDecimal("5000"));

        List<LowestPriceProjection> projections = Arrays.asList(projection1, projection2);
        when(categoryLowestPriceRepository.findLowestPriceByCategory()).thenReturn(projections);

        // when
        LowestPricePerCategoryResult result = lowestPriceService.findLowestPriceProductByAllCategories();

        // then
        assertNotNull(result);
        assertEquals(2, result.getLowestPricePerCategory().size());
        assertEquals(new BigDecimal("15000"), result.getTotalPrice());
        
    }
}