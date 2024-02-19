package com.napptilus.price.infrastructure.model;

import com.napptilus.price.domain.entity.Price;
import com.napptilus.price.domain.type.Currency;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "Prices")
public class PriceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_list")
    private Long priceList;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "brand_id")
    private Long brandId;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "price")
    private Double price;

    @Column(name = "curr")
    private String currency;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;


    public Price toEntity() {
        return Price.builder()
                .priceList(priceList)
                .productId(productId)
                .brandId(brandId)
                .priority(priority)
                .priceValue(price)
                .currency(Strings.isBlank(currency)  ? Currency.UNKNOWN : Currency.valueOf(currency))
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
}
