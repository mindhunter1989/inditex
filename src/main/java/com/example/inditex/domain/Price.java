package com.example.inditex.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@Builder
@AllArgsConstructor
public class Price {

    private Long id;
    private Long brandId;
    private Instant startDate;
    private Instant endDate;
    private Long priceList;
    private Long productId;
    private Long priority;
    private Float price;
    private String curr;

}
