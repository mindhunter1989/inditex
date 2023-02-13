package com.example.inditex.infrastructure.rest.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PriceResponse {

    @ApiModelProperty(notes = "Product identifier", example = "35455")
    private long productId;
    @ApiModelProperty(notes = "Brand identifier", example = "1")
    private long brandId;
    @ApiModelProperty(notes = "Price list", example = "1")
    private long priceList;
    @ApiModelProperty(notes = "Start date", example = "2020-06-14T00:00:00Z")
    private String startDate;
    @ApiModelProperty(notes = "End date", example = "2020-12-31T23:59:59Z")
    private String endDate;
    @ApiModelProperty(notes = "Price", example = "35.5")
    private float price;

}
