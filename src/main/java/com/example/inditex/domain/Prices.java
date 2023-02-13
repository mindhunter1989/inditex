package com.example.inditex.domain;

import java.time.Instant;

public interface Prices {
    Price findByApplicationDateAndProductIdAndBrandId(Instant applicationDate, Long productId, Long brandId);
}
