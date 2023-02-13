package com.example.inditex.infrastructure.rest.handler;

import com.example.inditex.infrastructure.persistence.sql.repository.SqlPrices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GetPriceByApplicationDateAndProductIdAndBrandIdHandlerIntegrationTest {

    @Autowired
    private SqlPrices repository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_retrieve_the_corresponding_price_for_2020_06_14_10_00_with_200_OK_status() throws Exception {
        mockMvc.perform(get("/price/fetchByApplicationDateAndProductIdAndBrandId")
                        .param("applicationDate", "2020-06-14T10:00:00.00Z")
                        .param("productId", "35455")
                        .param("brandId", "1"))
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
    void should_retrieve_the_corresponding_price_for_2020_06_14_16_00_with_200_OK_status() throws Exception {
        mockMvc.perform(get("/price/fetchByApplicationDateAndProductIdAndBrandId")
                        .param("applicationDate", "2020-06-14T16:00:00.00Z")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(2))
                .andExpect(jsonPath("$.startDate").value("2020-06-14T15:00:00Z"))
                .andExpect(jsonPath("$.endDate").value("2020-06-14T18:30:00Z"))
                .andExpect(jsonPath("$.price").value(25.45));
    }

    @Test
    void should_retrieve_the_corresponding_price_for_2020_06_14_21_00_with_200_OK_status() throws Exception {
        mockMvc.perform(get("/price/fetchByApplicationDateAndProductIdAndBrandId")
                        .param("applicationDate", "2020-06-14T21:00:00.00Z")
                        .param("productId", "35455")
                        .param("brandId", "1"))
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
    void should_retrieve_the_corresponding_price_for_2020_06_15_10_00_with_200_OK_status() throws Exception {
        mockMvc.perform(get("/price/fetchByApplicationDateAndProductIdAndBrandId")
                        .param("applicationDate", "2020-06-15T10:00:00.00Z")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(3))
                .andExpect(jsonPath("$.startDate").value("2020-06-15T00:00:00Z"))
                .andExpect(jsonPath("$.endDate").value("2020-06-15T11:00:00Z"))
                .andExpect(jsonPath("$.price").value(30.5));
    }

    @Test
    void should_retrieve_the_corresponding_price_for_2020_06_16_21_00_with_200_OK_status() throws Exception {
        mockMvc.perform(get("/price/fetchByApplicationDateAndProductIdAndBrandId")
                .param("applicationDate", "2020-06-16T21:00:00.00Z")
                .param("productId", "35455")
                .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(4))
                .andExpect(jsonPath("$.startDate").value("2020-06-15T16:00:00Z"))
                .andExpect(jsonPath("$.endDate").value("2020-12-31T23:59:59Z"))
                .andExpect(jsonPath("$.price").value(38.95));
    }

    @Test
    void should_retrieve_error_response_with_404_NOT_FOUND_status() throws Exception {
        mockMvc.perform(get("/price/fetchByApplicationDateAndProductIdAndBrandId")
                        .param("applicationDate", "2020-06-16T21:00:00.00Z")
                        .param("productId", "0")
                        .param("brandId", "0"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.httpErrorCode").value(404))
                .andExpect(jsonPath("$.message").value("Price not found for requested parameters!"));
    }

}
