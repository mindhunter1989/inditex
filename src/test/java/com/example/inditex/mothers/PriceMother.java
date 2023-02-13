package com.example.inditex.mothers;

import com.example.inditex.domain.Price;

public class PriceMother {

    static final Long id = 1L;

    private final InstantMother instantMother = new InstantMother();

    public Price simple() {
        return Price.builder()
                .id(id)
                .brandId(1L)
                .startDate(instantMother.june_14th_2020_00_00())
                .endDate(instantMother.december_31th_2020_23_59())
                .priceList(1L)
                .productId(35455L)
                .priority(0L)
                .price(35.5F)
                .curr("EUR")
                .build();
    }

}
