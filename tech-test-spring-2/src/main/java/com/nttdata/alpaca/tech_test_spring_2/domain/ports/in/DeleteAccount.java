package com.nttdata.alpaca.tech_test_spring_2.domain.ports.in;

import reactor.core.publisher.Mono;

public interface DeleteAccount {
    Mono<Void> deleteAccountById(Long id);
}
