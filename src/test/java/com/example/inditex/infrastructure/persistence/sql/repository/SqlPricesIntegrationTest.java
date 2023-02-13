package com.example.inditex.infrastructure.persistence.sql.repository;

import com.example.inditex.infrastructure.persistence.sql.model.SqlPrice;
import com.example.inditex.mothers.InstantMother;
import com.example.inditex.mothers.SqlPriceMother;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class SqlPricesIntegrationTest {

    @Autowired
    private SqlPrices repository;

    static InstantMother instantMother;
    static SqlPriceMother sqlPriceMother;

    @BeforeAll
    static void setUp() {
        instantMother = new InstantMother();
        sqlPriceMother = new SqlPriceMother();
    }

    @Test
    public void should_retrieve_the_corresponding_price_for_2020_06_14_10_00() {
        SqlPrice sqlPrice = sqlPriceMother.simple();
        Instant applicationDate = instantMother.june_14th_2020_10_00();

        Page<SqlPrice> sqlPriceFound = repository.findByApplicationDateAndProductIdAndBrandId(
                PageRequest.of(0, 1),
                applicationDate,
                sqlPrice.getProductId(),
                sqlPrice.getBrandId()
        );

        assertThat(sqlPriceFound.hasContent())
                .isEqualTo(true);
        assertThat(sqlPriceFound.getContent().get(0).getPrice())
                .isEqualTo(sqlPrice.getPrice());
    }

}
