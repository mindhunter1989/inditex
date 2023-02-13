package com.example.inditex.infrastructure.rest.handler;

import com.example.inditex.action.GetPriceByApplicationDateAndProductIdAndBrandId;
import com.example.inditex.domain.Price;
import com.example.inditex.domain.exception.PriceNotFound;
import com.example.inditex.infrastructure.rest.response.ErrorResponse;
import com.example.inditex.infrastructure.rest.response.PriceResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@Api(tags = { "Prices Controller" } )
@RestController
@RequestMapping("/price")
public class GetPriceByApplicationDateAndProductIdAndBrandIdHandler {

    @Autowired
    private GetPriceByApplicationDateAndProductIdAndBrandId action;

    @ApiOperation(value = "Endpoint that fetch prices by dates, product and brand")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The operation was succesful"),
            @ApiResponse(code = 404, message = "Price not found") } )
    @GetMapping("/fetchByApplicationDateAndProductIdAndBrandId")
    @ResponseStatus(HttpStatus.OK)
    public PriceResponse fetchByApplicationDateAndProductIdAndBrandId(
            @ApiParam(name = "applicationDate",
                    value = "Date on which you want to apply the price",
                    required = true,
                    example = "2020-06-14T10:00:00.00Z")
            @RequestParam String applicationDate,
            @ApiParam(name = "productId",
                    value = "Product identifier",
                    required = true,
                    example = "35455")
            @RequestParam String productId,
            @ApiParam(name = "brandId",
                    value = "Brand identifier",
                    required = true,
                    example = "1")
            @RequestParam String brandId
    ) {
        Price price = action.getPriceByApplicationDateAndProductIdAndBrandId(
                Instant.parse(applicationDate), Long.valueOf(productId), Long.valueOf(brandId));

        return toResponse(price);
    }

    private PriceResponse toResponse(Price price) {
        return PriceResponse.builder()
                .productId(price.getProductId())
                .brandId(price.getBrandId())
                .priceList(price.getPriceList())
                .startDate(price.getStartDate().toString())
                .endDate(price.getEndDate().toString())
                .price(price.getPrice())
                .build();
    }

    @ExceptionHandler(PriceNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlePriceNotFound(PriceNotFound e) {
        return ErrorResponse.builder()
                .httpErrorCode(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .build();
    }

}
