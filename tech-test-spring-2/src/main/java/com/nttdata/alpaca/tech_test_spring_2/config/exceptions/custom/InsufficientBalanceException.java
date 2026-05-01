package com.nttdata.alpaca.tech_test_spring_2.config.exceptions.custom;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
