package com.napptilus.price.entrypoint.rest;

import com.napptilus.price.domain.entity.Price;
import com.napptilus.price.domain.type.Currency;
import lombok.Builder;
import lombok.Getter;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.util.Locale;

@Builder
@Getter
public class PriceResponse {
    private Long priceList;
    private Long productId;
    private Long brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String price;

    public static PriceResponse fromEntity(Price price) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator('.');

        DecimalFormat df = new DecimalFormat("0.00", symbols);

        return PriceResponse.builder()
                .brandId(price.getBrandId())
                .startDate(price.getStartDate())
                .endDate(price.getEndDate())
                .priceList(price.getPriceList())
                .productId(price.getProductId())
                .price(String.format("%s %s", df.format(price.getPriceValue()), Currency.getDescription(price.getCurrency())))
                .build();
    }
}
