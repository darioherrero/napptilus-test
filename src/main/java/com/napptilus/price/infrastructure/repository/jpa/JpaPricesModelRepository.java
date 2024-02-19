package com.napptilus.price.infrastructure.repository.jpa;

import com.napptilus.price.infrastructure.model.PriceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JpaPricesModelRepository extends JpaRepository<PriceModel, Long> {

    @Query("SELECT p FROM PriceModel p WHERE p.productId = :productId AND p.brandId = :brandId AND " +
            "p.startDate <= :date AND p.endDate >= :date ORDER BY p.priority DESC")
    List<PriceModel> findAllByProductAndBrandAndDate(@Param("productId") Long productId,
                                                     @Param("brandId") Long brandId,
                                                     @Param("date") LocalDateTime date);
}