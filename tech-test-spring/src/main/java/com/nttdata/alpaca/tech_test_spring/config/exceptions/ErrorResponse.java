package com.nttdata.alpaca.tech_test_spring.config.exceptions;

import java.time.Instant;

public record ErrorResponse(int status, String message, String path, Instant timestamp) {

}
