package com.napptilus.price.entrypoint.rest;

import com.napptilus.price.application.GetPriceToApplyUseCase;
import com.napptilus.price.domain.entity.Price;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PriceController {

    private final GetPriceToApplyUseCase getPriceToApplyUseCase;

    @GetMapping("/price")
    @Operation(summary = "Find price to apply based on application date, product id and brand id")
    public ResponseEntity<PriceResponse> findPriceToApply(@Parameter(example = "2020-06-14T10:00:00") @RequestParam String applicationDate,
                                                          @RequestParam Long productId,
                                                          @RequestParam Long brandId) {

        Optional<Price> price = getPriceToApplyUseCase
                .execute(GetPriceToApplyUseCase.Request.of(productId, brandId, applicationDate));

        return ResponseEntity.ok(price.map(PriceResponse::fromEntity).orElse(null));
    }
}