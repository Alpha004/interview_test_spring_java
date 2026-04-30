package com.nttdata.alpaca.tech_test_spring_2.config.exceptions.custom;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
