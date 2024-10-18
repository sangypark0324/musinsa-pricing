package com.musinsa.pricing.repository;

import com.musinsa.pricing.domain.CategoryAggregateInfo;
import com.musinsa.pricing.domain.projection.CategoryAggregateInfoProjection;
import com.musinsa.pricing.domain.projection.LowestPriceProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryAggregateInfoRepository extends JpaRepository<CategoryAggregateInfo,Long> {

    @Query(value = "SELECT " +
            "       c.id AS categoryId, " +
            "       c.name AS categoryName, " +
            "       b.id as brandId, " +
            "       b.name AS brandName, " +
            "       p.id as productId, " +
            "       clp.lowest_price AS lowestPrice " +
            "FROM category_aggregate_info clp " +
            "JOIN product p ON clp.lowest_price_product_id = p.id " +
            "JOIN brand b ON p.brand_id = b.id " +
            "JOIN category c ON c.id = clp.category_id ",
            nativeQuery = true)
    List<LowestPriceProjection> findLowestPriceByCategory();

    @Query(value = "SELECT c.name AS categoryName, " +
            "lp.price AS lowestPrice, lpb.name AS lowestPriceBrand, " +
            "hp.price AS highestPrice, hpb.name AS highestPriceBrand " +
            "FROM category_aggregate_info cai " +
            "JOIN product lp ON cai.lowest_price_product_id = lp.id " +
            "JOIN product hp ON cai.highest_price_product_id = hp.id " +
            "JOIN brand lpb ON lp.brand_id = lpb.id " +
            "JOIN brand hpb ON hp.brand_id = hpb.id " +
            "JOIN category c ON cai.category_id = c.id " +
            "WHERE c.name = :categoryName", nativeQuery = true)
    Optional<CategoryAggregateInfoProjection> findCategoryAggregateInfoByCategoryName(@Param("categoryName") String categoryName);

}
