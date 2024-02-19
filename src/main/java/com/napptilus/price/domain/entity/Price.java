package com.napptilus.price.domain.entity;

import com.napptilus.price.domain.type.Currency;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class Price {
    private Long priceList;
    private Long brandId;
    private Long productId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer priority;
    private Double priceValue;
    private Currency currency;
}
