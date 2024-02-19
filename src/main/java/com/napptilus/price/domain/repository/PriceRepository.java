package com.napptilus.price.domain.repository;

import com.napptilus.price.domain.entity.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository {
    List<Price> findByProductBrandAndDate(Long productId, Long brandId, LocalDateTime date);
}
