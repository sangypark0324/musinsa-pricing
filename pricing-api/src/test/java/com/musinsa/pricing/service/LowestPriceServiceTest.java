package com.musinsa.pricing.service;

import com.musinsa.pricing.domain.projection.BrandTotalLowestPriceProjection;
import com.musinsa.pricing.domain.projection.LowestPriceProjection;
import com.musinsa.pricing.model.response.LowestBrandResult;
import com.musinsa.pricing.model.response.LowestPricePerCategoryResult;
import com.musinsa.pricing.repository.BrandRepository;
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

    @Mock
    private BrandRepository brandRepository;

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

    @Test
    public void 단일_브랜드의_모든카테고리에서_최저가상품을_구매할때의_최저가브랜드명과_전체금액을_구한다() {
        // Given
        final String BRAND_NAME = "A";
        BrandTotalLowestPriceProjection mockBrandProjection = mock(BrandTotalLowestPriceProjection.class);
        when(mockBrandProjection.getBrandId()).thenReturn(1L);
        when(mockBrandProjection.getBrandName()).thenReturn(BRAND_NAME);
        when(mockBrandProjection.getTotalLowestPrice()).thenReturn(BigDecimal.valueOf(10000));

        LowestPriceProjection mockLowestPriceProjection1 = mock(LowestPriceProjection.class);
        when(mockLowestPriceProjection1.getCategoryName()).thenReturn("상의");
        when(mockLowestPriceProjection1.getLowestPrice()).thenReturn(BigDecimal.valueOf(6000));

        LowestPriceProjection mockLowestPriceProjection2 = mock(LowestPriceProjection.class);
        when(mockLowestPriceProjection2.getCategoryName()).thenReturn("하의");
        when(mockLowestPriceProjection2.getLowestPrice()).thenReturn(BigDecimal.valueOf(4000));

        List<LowestPriceProjection> mockLowestPriceProjections = List.of(mockLowestPriceProjection1, mockLowestPriceProjection2);

        when(brandRepository.findBrandWithLowestTotalPrice()).thenReturn(mockBrandProjection);
        when(brandRepository.findLowestPriceProductsByBrandId(1L)).thenReturn(mockLowestPriceProjections);


        // When
        LowestBrandResult result = lowestPriceService.getBrandWithLowestTotalPrice();

        // Then: Verify the result
        assertEquals(BRAND_NAME, result.getLowestPriceBrandInfo().getBrandName());
        assertEquals(BigDecimal.valueOf(10000), result.getLowestPriceBrandInfo().getTotalPrice());
    }
}