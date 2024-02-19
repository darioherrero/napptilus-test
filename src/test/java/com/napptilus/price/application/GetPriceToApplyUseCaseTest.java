package com.napptilus.price.application;

import com.napptilus.price.application.exception.InvalidRequestException;
import com.napptilus.price.domain.entity.Price;
import com.napptilus.price.domain.service.PriceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetPriceToApplyUseCaseTest {

    @Mock
    private PriceService priceService;

    @InjectMocks
    private GetPriceToApplyUseCase getPriceToApplyUseCase;

    @Test
    void execute_with_valid_request_should_call_price_service_and_return_its_response() {
        // Given
        Long productId = 1L;
        Long brandId = 1L;
        LocalDateTime date = LocalDateTime.of(2023, 6, 14, 10, 0, 0);
        GetPriceToApplyUseCase.Request request = GetPriceToApplyUseCase.Request.of(productId, brandId, date.toString());

        Price price = Price.builder().priority(1).priceValue(500.00).build();

        when(priceService.findPriceToApply(productId, brandId, date)).thenReturn(Optional.of(price));

        // When
        Optional<Price> result = getPriceToApplyUseCase.execute(request);

        // Then
        assertTrue(result.isPresent(), "A price should have been returned");
        assertEquals(price.getPriceValue(), result.get().getPriceValue(),
                "The returned price should have a value of 500.00");
    }

    @Test
    void execute_with_invalid_request_should_throw_invalid_request_exception() {
        GetPriceToApplyUseCase.Request invalidRequest = GetPriceToApplyUseCase.Request.of(null, null, null);

        Exception exception = assertThrows(InvalidRequestException.class,
                () -> getPriceToApplyUseCase.execute(invalidRequest));

        String expectedMessage = "All fields (productId, brandId and date) should have a value.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}