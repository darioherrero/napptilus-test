package com.napptilus.price.application;

import com.napptilus.price.application.exception.InvalidRequestException;
import com.napptilus.price.domain.entity.Price;
import com.napptilus.price.domain.service.PriceService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetPriceToApplyUseCase {

    private final PriceService priceService;

    @AllArgsConstructor(staticName = "of")
    @Getter
    public static class Request {
        private Long productId;
        private Long brandId;
        private String date;

        public boolean isValid() {
            return productId != null && brandId != null && date != null;
        }
    }

    public Optional<Price> execute(Request request) {
        if (!request.isValid()) {
            throw new InvalidRequestException("All fields (productId, brandId and date) should have a value.");
        }

        LocalDateTime date;

        try {
            date = LocalDateTime.parse(request.getDate());
        } catch (Exception e) {
            throw new InvalidRequestException("Requested application date has invalid value.");
        }

        return priceService.findPriceToApply(request.getProductId(), request.getBrandId(), date);
    }

}
