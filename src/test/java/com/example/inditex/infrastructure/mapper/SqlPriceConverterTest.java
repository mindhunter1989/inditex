package com.example.inditex.infrastructure.mapper;

import com.example.inditex.domain.Price;
import com.example.inditex.infrastructure.persistence.sql.model.SqlPrice;
import com.example.inditex.infrastructure.persistence.sql.repository.SqlPrices;
import com.example.inditex.mothers.InstantMother;
import com.example.inditex.mothers.SqlPriceMother;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class SqlPriceConverterTest {

    private static InstantMother instantMother;
    private static SqlPriceMother sqlPriceMother;
    private SqlPrice sqlPrice;

    @MockBean
    private SqlPrices repository;

    private SqlPriceConverter sut;

    @BeforeAll
    static void setUpAll() {
        instantMother = new InstantMother();
        sqlPriceMother = new SqlPriceMother();
    }

    @BeforeEach
    void setUp() {
        sut = new SqlPriceConverter(repository);

        sqlPrice = sqlPriceMother.simple();
        Page<SqlPrice> sqlPricePage = new PageImpl<>(Collections.singletonList(sqlPrice));

        given(repository.findByApplicationDateAndProductIdAndBrandId(
                PageRequest.of(0, 1),
                instantMother.june_14th_2020_10_00(),
                sqlPrice.getProductId(),
                sqlPrice.getBrandId()
        )).willReturn(sqlPricePage);
    }

    @Test
    public void should_retrieve_the_corresponding_price_for_2020_06_14_10_00() {
        Price priceFound = sut.findByApplicationDateAndProductIdAndBrandId(
                instantMother.june_14th_2020_10_00(),
                sqlPriceMother.simple().getProductId(),
                sqlPriceMother.simple().getBrandId()
        );

        assertThat(priceFound.getPrice())
                .isEqualTo(sqlPrice.getPrice());
    }

}
