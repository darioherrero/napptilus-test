package com.napptilus.price.domain.service;

import com.napptilus.price.domain.entity.Price;
import com.napptilus.price.domain.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PriceService {
    private final PriceRepository priceRepository;

    /**
     * Method that contains business logic to get the right price to apply.
     * That was defined as the price with max priority for a brand and product for a given datetime.
     *
     * @param productId - id of the product
     * @param brandId -  id of the product brand
     * @param date - datetime for which the price is required
     * @return an Optional object which will hold the Price object when found
     */
    public Optional<Price> findPriceToApply(Long productId, Long brandId, LocalDateTime date) {
        List<Price> prices = priceRepository.findByProductBrandAndDate(productId, brandId, date);

        return prices.stream().max(Comparator.comparing(Price::getPriority));
    }
}
