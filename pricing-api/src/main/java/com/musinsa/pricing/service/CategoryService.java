package com.musinsa.pricing.service;

import com.musinsa.pricing.domain.Category;
import com.musinsa.pricing.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
