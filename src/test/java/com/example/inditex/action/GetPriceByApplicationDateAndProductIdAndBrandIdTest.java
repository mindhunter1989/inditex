package com.example.inditex.action;

import com.example.inditex.domain.Price;
import com.example.inditex.domain.Prices;
import com.example.inditex.mothers.InstantMother;
import com.example.inditex.mothers.PriceMother;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class GetPriceByApplicationDateAndProductIdAndBrandIdTest {

    private static InstantMother instantMother;
    private static PriceMother priceMother;
    private Price price;

    @MockBean
    private Prices repository;

    private GetPriceByApplicationDateAndProductIdAndBrandId sut;

    @BeforeAll
    static void setUpAll() {
        instantMother = new InstantMother();
        priceMother = new PriceMother();
    }

    @BeforeEach
    void setUp() {
        sut = new GetPriceByApplicationDateAndProductIdAndBrandId(repository);

        price = priceMother.simple();

        given(repository.findByApplicationDateAndProductIdAndBrandId(
                instantMother.june_14th_2020_10_00(),
                price.getProductId(),
                price.getBrandId()
        )).willReturn(price);
    }

    @Test
    public void should_retrieve_the_corresponding_price_for_2020_06_14_10_00() {
        Price priceFound = sut.getPriceByApplicationDateAndProductIdAndBrandId(
                instantMother.june_14th_2020_10_00(),
                priceMother.simple().getProductId(),
                priceMother.simple().getBrandId()
        );

        assertThat(priceFound.getPrice())
                .isEqualTo(price.getPrice());
    }

}
