package com.musinsa.pricing.repository;

import com.musinsa.pricing.domain.BrandCategoryLowestPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandCategoryLowestPriceRepository extends JpaRepository<BrandCategoryLowestPrice,Long> {


}
