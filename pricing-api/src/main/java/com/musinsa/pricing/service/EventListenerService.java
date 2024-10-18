package com.musinsa.pricing.service;

import com.musinsa.pricing.domain.CategoryAggregateInfo;
import com.musinsa.pricing.domain.Product;
import com.musinsa.pricing.domain.event.UpdatePriceEvent;
import com.musinsa.pricing.exception.BusinessRuleException;
import com.musinsa.pricing.exception.ErrorType;
import com.musinsa.pricing.repository.CategoryAggregateInfoRepository;
import com.musinsa.pricing.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventListenerService {

    private final CategoryAggregateInfoRepository categoryAggregateInfoRepository;
    private final ProductRepository productRepository;

    @Async
    @EventListener
    public void handleEvent(UpdatePriceEvent event) throws InterruptedException {
        log.info("[PriceChangedEventListener] handleEvent : {}", event);
        CategoryAggregateInfo categoryAggregateInfo =
                categoryAggregateInfoRepository.findById(event.categoryId()).orElseThrow(() -> new BusinessRuleException("카테고리별 집계정보가 존재하지 않습니다.", ErrorType.NO_RESOURCE));
        Product product = productRepository.findById(event.productId()).orElseThrow(()-> new BusinessRuleException("상품정보가 존재하지 않습니다.",ErrorType.NO_RESOURCE));
        categoryAggregateInfo.updatePriceInfo(event.price(),product);
        categoryAggregateInfoRepository.save(categoryAggregateInfo);
    }

}
