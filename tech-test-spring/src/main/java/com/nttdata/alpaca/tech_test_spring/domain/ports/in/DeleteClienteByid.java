package com.nttdata.alpaca.tech_test_spring.domain.ports.in;

import reactor.core.publisher.Mono;

public interface DeleteClienteByid {
    Mono<Void> deleteClienteById(Long id);
}
