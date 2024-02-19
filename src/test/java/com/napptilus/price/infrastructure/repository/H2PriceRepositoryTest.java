package com.napptilus.price.infrastructure.repository;

import com.napptilus.price.domain.entity.Price;
import com.napptilus.price.infrastructure.model.PriceModel;
import com.napptilus.price.infrastructure.repository.jpa.JpaPricesModelRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class H2PriceRepositoryTest {

    @Mock
    private JpaPricesModelRepository jpaPricesModelRepository;

    @InjectMocks
    private H2PriceRepository h2PriceRepository;

    @Test
    void findByProductBrandAndDate_should_call_jpa_repository_and_return_found_models_mapped_as_price_entities() {
        // Given
        Long productId = 1L;
        Long brandId = 1L;
        LocalDateTime applicationDate = LocalDateTime.of(2023, 6, 14, 10, 0, 0);

        PriceModel priceModel1 = new PriceModel().setPriceList(1L).setPriority(1).setPrice(500.00).setCurrency("EUR");
        PriceModel priceModel2 = new PriceModel().setPriceList(2L).setPriority(2).setPrice(700.00).setCurrency("EUR");

        List<PriceModel> priceModels = Arrays.asList(priceModel1, priceModel2);

        when(jpaPricesModelRepository.findAllByProductAndBrandAndDate(productId, brandId, applicationDate))
                .thenReturn(priceModels);

        // When
        List<Price> result = h2PriceRepository.findByProductBrandAndDate(productId, brandId, applicationDate);

        // Then
        verify(jpaPricesModelRepository, times(1))
                .findAllByProductAndBrandAndDate(productId, brandId, applicationDate);

        assertEquals(priceModels.size(), result.size());

        Optional<Price> priceModel1Optional = result.stream().filter(r -> r.getPriceList().equals(priceModel1.getPriceList())).findAny();
        assertTrue(priceModel1Optional.isPresent());
        assertEquals(priceModel1.getPrice(), priceModel1Optional.get().getPriceValue());
        assertEquals(priceModel1.getPriority(), priceModel1Optional.get().getPriority());

        Optional<Price> priceModel2Optional = result.stream().filter(r -> r.getPriceList().equals(priceModel2.getPriceList())).findAny();
        assertTrue(priceModel2Optional.isPresent());
        assertEquals(priceModel2.getPrice(), priceModel2Optional.get().getPriceValue());
        assertEquals(priceModel2.getPriority(), priceModel2Optional.get().getPriority());
    }
}