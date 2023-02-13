package com.example.inditex.infrastructure.mapper;

import com.example.inditex.domain.Price;
import com.example.inditex.domain.Prices;
import com.example.inditex.domain.exception.PriceNotFound;
import com.example.inditex.infrastructure.persistence.sql.model.SqlPrice;
import com.example.inditex.infrastructure.persistence.sql.repository.SqlPrices;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@AllArgsConstructor
public class SqlPriceConverter implements Prices {

    @Autowired
    SqlPrices sqlRepository;

    @Override
    public Price findByApplicationDateAndProductIdAndBrandId(Instant applicationDate, Long productId, Long brandId) {
        Page<SqlPrice> sqlPrice = sqlRepository.findByApplicationDateAndProductIdAndBrandId(
                PageRequest.of(0, 1),
                applicationDate,
                productId,
                brandId
        );

        if (!sqlPrice.hasContent()) {
            throw new PriceNotFound();
        }

        return toDomainPrice(sqlPrice);
    }

    private Price toDomainPrice(Page<SqlPrice> sqlPrice) {
        return Price.builder()
                .id(sqlPrice.getContent().get(0).getId())
                .brandId(sqlPrice.getContent().get(0).getBrandId())
                .startDate(sqlPrice.getContent().get(0).getStartDate())
                .endDate(sqlPrice.getContent().get(0).getEndDate())
                .priceList(sqlPrice.getContent().get(0).getPriceList())
                .productId(sqlPrice.getContent().get(0).getProductId())
                .priority(sqlPrice.getContent().get(0).getPriority())
                .price(sqlPrice.getContent().get(0).getPrice())
                .curr(sqlPrice.getContent().get(0).getCurr())
                .build();
    }

}
