package com.musinsa.pricing.repository;


import com.musinsa.pricing.domain.Brand;
import com.musinsa.pricing.domain.projection.BrandTotalLowestPriceProjection;
import com.musinsa.pricing.domain.projection.LowestPriceProductionProjection;
import com.musinsa.pricing.domain.projection.LowestPriceProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Long> {
    @Query(value =
                    "SELECT   b.id as brandId, " +
                    "         b.name as brandName, " +
                    "         SUM(min_price) AS totalLowestPrice " +
                    "FROM ( " +
                    "       SELECT " +
                    "           brand_id, " +
                    "           category_id, " +
                    "           MIN(price) AS min_price " +
                    "       FROM product p " +
                    "       GROUP BY brand_id, category_id " +
                    "     ) AS brand_category_min_price " +
                    "JOIN brand b ON b.id = brand_category_min_price.brand_id " +
                    "GROUP BY b.id " +
                    "ORDER BY totalLowestPrice ASC " +
                    "LIMIT 1", nativeQuery = true)
    BrandTotalLowestPriceProjection findBrandWithLowestTotalPrice();


    @Query(value =
            "SELECT " +
                    "       c.id AS categoryId, " +
                    "       c.name AS categoryName, " +
                    "       MIN(p.price) AS lowestPrice " +
                    "FROM product p " +
                    "JOIN category c ON p.category_id = c.id " +
                    "WHERE p.brand_id = :brandId " +
                    "GROUP BY c.id, c.name " +
                    "ORDER BY lowestPrice ASC",
            nativeQuery = true)
    List<LowestPriceProjection> findLowestPriceProductsByBrandId(Long brandId);

}
