package com.nttdata.alpaca.tech_test_spring.domain.ports.in;

import com.nttdata.alpaca.tech_test_spring.domain.models.Cliente;
import reactor.core.publisher.Mono;

public interface GetClienteById {
    Mono<Cliente> getClienteById(Long id);
}
