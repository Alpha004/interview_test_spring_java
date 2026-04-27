package com.nttdata.alpaca.tech_test_spring.domain.ports.in;

import com.nttdata.alpaca.tech_test_spring.domain.models.Cliente;
import reactor.core.publisher.Flux;

public interface GetAllClientes {
    Flux<Cliente> getAllClientes();
}
