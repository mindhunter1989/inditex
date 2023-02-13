package com.example.inditex.domain.exception;

public class PriceNotFound extends RuntimeException {

    public PriceNotFound() {
        super("Price not found for requested parameters!");
    }

}
