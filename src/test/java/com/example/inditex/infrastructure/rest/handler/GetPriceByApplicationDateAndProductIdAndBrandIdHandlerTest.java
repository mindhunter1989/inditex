package com.example.inditex.infrastructure.rest.handler;

import com.example.inditex.action.GetPriceByApplicationDateAndProductIdAndBrandId;
import com.example.inditex.domain.Price;
import com.example.inditex.domain.exception.PriceNotFound;
import com.example.inditex.mothers.InstantMother;
import com.example.inditex.mothers.PriceMother;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GetPriceByApplicationDateAndProductIdAndBrandIdHandler.class)
public class GetPriceByApplicationDateAndProductIdAndBrandIdHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetPriceByApplicationDateAndProductIdAndBrandId action;

    static InstantMother instantMother;
    static PriceMother priceMother;

    @BeforeAll
    static void setUp() {
        instantMother = new InstantMother();
        priceMother = new PriceMother();
    }

    @Test
    void should_retrieve_the_corresponding_price_for_2020_06_14_10_00_with_200_OK_status() throws Exception {
        Price price = priceMother.simple();
        Instant applicationDate = instantMother.june_14th_2020_10_00();

        given(action.getPriceByApplicationDateAndProductIdAndBrandId(
                applicationDate,
                price.getProductId(),
                price.getBrandId()
        )).willReturn(price);

        mockMvc.perform(get("/price/fetchByApplicationDateAndProductIdAndBrandId")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", price.getProductId().toString())
                        .param("brandId", price.getBrandId().toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.startDate").value("2020-06-14T00:00:00Z"))
                .andExpect(jsonPath("$.endDate").value("2020-12-31T23:59:59Z"))
                .andExpect(jsonPath("$.price").value(35.5));
    }

    @Test
    void should_retrieve_error_response_with_404_NOT_FOUND_status() throws Exception {
        Price price = priceMother.simple();
        Instant applicationDate = instantMother.june_14th_2020_10_00();

        given(action.getPriceByApplicationDateAndProductIdAndBrandId(
                applicationDate,
                price.getProductId(),
                price.getBrandId()
        )).willThrow(new PriceNotFound());

        mockMvc.perform(get("/price/fetchByApplicationDateAndProductIdAndBrandId")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", price.getProductId().toString())
                        .param("brandId", price.getBrandId().toString()))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.httpErrorCode").value(404))
                .andExpect(jsonPath("$.message").value("Price not found for requested parameters!"));
    }

}
