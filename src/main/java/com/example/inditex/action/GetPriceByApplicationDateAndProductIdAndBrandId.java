package com.example.inditex.action;

import com.example.inditex.domain.Price;
import com.example.inditex.domain.Prices;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@AllArgsConstructor
public class GetPriceByApplicationDateAndProductIdAndBrandId {

    @Autowired
    private Prices repository;

    public Price getPriceByApplicationDateAndProductIdAndBrandId(
            Instant applicationDate,
            Long productId,
            Long brandId
    ) {
        return repository.findByApplicationDateAndProductIdAndBrandId(applicationDate, productId, brandId);
    }

}
