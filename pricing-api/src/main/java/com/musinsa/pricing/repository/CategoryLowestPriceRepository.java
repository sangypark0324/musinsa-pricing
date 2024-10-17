package com.musinsa.pricing.repository;

import com.musinsa.pricing.domain.CategoryLowestPrice;
import com.musinsa.pricing.domain.projection.LowestPricePerCategoryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryLowestPriceRepository extends JpaRepository<CategoryLowestPrice,Long> {

    @Query(value = "SELECT " +
            "       c.id AS categoryId, " +
            "       c.name AS categoryName, " +
            "       b.id as brandId, " +
            "       b.name AS brandName, " +
            "       p.id as productId, " +
            "       clp.lowest_price AS lowestPrice " +
            "FROM category_lowest_price clp " +
            "JOIN product p ON clp.product_id = p.id " +
            "JOIN brand b ON p.brand_id = b.id " +
            "JOIN category c ON c.id = clp.category_id ",
            nativeQuery = true)
    List<LowestPricePerCategoryProjection> findLowestPriceByCategory();
}
