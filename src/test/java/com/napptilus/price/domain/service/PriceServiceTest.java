package com.napptilus.price.domain.service;

import com.napptilus.price.domain.entity.Price;
import com.napptilus.price.domain.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceService priceService;

    @Test
    void findPriceToApply_should_return_max_priority_price_for_a_given_datetime() {
        // Given
        Long productId = 1L;
        Long brandId = 1L;
        LocalDateTime applicationDate = LocalDateTime.of(2023, 6, 14, 10, 0, 0);

        Price priceWithLowPriority = Price.builder().priceList(1L).priority(1).build();
        Price priceWithHighPriority = Price.builder().priceList(2L).priority(2).build();

        List<Price> prices = Arrays.asList(priceWithHighPriority, priceWithLowPriority);

        when(priceRepository.findByProductBrandAndDate(productId, brandId, applicationDate)).thenReturn(prices);

        // When
        Optional<Price> result = priceService.findPriceToApply(productId, brandId, applicationDate);

        // Then
        assertTrue(result.isPresent());
        assertEquals(priceWithHighPriority.getPriceList(), result.get().getPriceList(),
                "The price with the highest priority should be returned");
    }
}