package com.napptilus.price.entrypoint.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PriceControllerTest {

    public static final int PRODUCT_ID = 35455;
    public static final int BRAND_ID = 1;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void findPriceToApply_should_return_price_3550_for_date_20200614_time_100000() throws Exception {
        String url = "/price?applicationDate=2020-06-14T10:00:00&productId=35455&brandId=1";

        ResultActions resultActions = mockMvc
                .perform(get(url).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        resultActions.andExpect(jsonPath("$.brandId", is(BRAND_ID)));
        resultActions.andExpect(jsonPath("$.startDate", is("2020-06-14T00:00:00")));
        resultActions.andExpect(jsonPath("$.endDate", is("2020-12-31T23:59:59")));
        resultActions.andExpect(jsonPath("$.priceList", is(1)));
        resultActions.andExpect(jsonPath("$.productId", is(PRODUCT_ID)));
        resultActions.andExpect(jsonPath("$.price", is("35.50 €")));
    }

    @Test
    void findPriceToApply_should_return_price_2545_for_date_20200614_time_160000() throws Exception {
        String url = "/price?applicationDate=2020-06-14T16:00:00&productId=35455&brandId=1";

        ResultActions resultActions = mockMvc
                .perform(get(url).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        resultActions.andExpect(jsonPath("$.brandId", is(BRAND_ID)));
        resultActions.andExpect(jsonPath("$.startDate", is("2020-06-14T15:00:00")));
        resultActions.andExpect(jsonPath("$.endDate", is("2020-06-14T18:30:00")));
        resultActions.andExpect(jsonPath("$.priceList", is(2)));
        resultActions.andExpect(jsonPath("$.productId", is(PRODUCT_ID)));
        resultActions.andExpect(jsonPath("$.price", is("25.45 €")));
    }

    @Test
    void findPriceToApply_should_return_price_3550_for_date_20200614_time_210000() throws Exception {
        String url = "/price?applicationDate=2020-06-14T21:00:00&productId=35455&brandId=1";

        ResultActions resultActions = mockMvc
                .perform(get(url).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        resultActions.andExpect(jsonPath("$.brandId", is(BRAND_ID)));
        resultActions.andExpect(jsonPath("$.startDate", is("2020-06-14T00:00:00")));
        resultActions.andExpect(jsonPath("$.endDate", is("2020-12-31T23:59:59")));
        resultActions.andExpect(jsonPath("$.priceList", is(1)));
        resultActions.andExpect(jsonPath("$.productId", is(PRODUCT_ID)));
        resultActions.andExpect(jsonPath("$.price", is("35.50 €")));
    }

    @Test
    void findPriceToApply_should_return_price_3050_for_date_20200615_time_100000() throws Exception {
        String url = "/price?applicationDate=2020-06-15T10:00:00&productId=35455&brandId=1";

        ResultActions resultActions = mockMvc
                .perform(get(url).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        resultActions.andExpect(jsonPath("$.brandId", is(BRAND_ID)));
        resultActions.andExpect(jsonPath("$.startDate", is("2020-06-15T00:00:00")));
        resultActions.andExpect(jsonPath("$.endDate", is("2020-06-15T11:00:00")));
        resultActions.andExpect(jsonPath("$.priceList", is(3)));
        resultActions.andExpect(jsonPath("$.productId", is(PRODUCT_ID)));
        resultActions.andExpect(jsonPath("$.price", is("30.50 €")));
    }

    @Test
    void findPriceToApply_should_return_price_3895_for_date_20200616_time_210000() throws Exception {
        String url = "/price?applicationDate=2020-06-16T21:00:00&productId=35455&brandId=1";

        ResultActions resultActions = mockMvc
                .perform(get(url).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        resultActions.andExpect(jsonPath("$.brandId", is(BRAND_ID)));
        resultActions.andExpect(jsonPath("$.startDate", is("2020-06-15T16:00:00")));
        resultActions.andExpect(jsonPath("$.endDate", is("2020-12-31T23:59:59")));
        resultActions.andExpect(jsonPath("$.priceList", is(4)));
        resultActions.andExpect(jsonPath("$.productId", is(PRODUCT_ID)));
        resultActions.andExpect(jsonPath("$.price", is("38.95 €")));
    }
}