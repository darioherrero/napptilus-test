package com.napptilus.price.infrastructure.repository;

import com.napptilus.price.domain.entity.Price;
import com.napptilus.price.domain.repository.PriceRepository;
import com.napptilus.price.infrastructure.model.PriceModel;
import com.napptilus.price.infrastructure.repository.jpa.JpaPricesModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class H2PriceRepository implements PriceRepository {

    private final JpaPricesModelRepository jpaPricesModelRepository;

    @Override
    public List<Price> findByProductBrandAndDate(Long productId, Long brandId, LocalDateTime date) {
        return jpaPricesModelRepository.findAllByProductAndBrandAndDate(productId, brandId, date)
                .stream().map(PriceModel::toEntity)
                .collect(Collectors.toList());
    }
}
