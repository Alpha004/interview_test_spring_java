package com.nttdata.alpaca.tech_test_spring.domain.ports.out;

import org.springframework.data.domain.Pageable;

import com.nttdata.alpaca.tech_test_spring.domain.models.Cliente;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClienteRepositoryPort {
    Flux<Cliente> getAllClientes(Pageable pageable);
    Mono<Cliente> saveCliente(Cliente cliente);
    Mono<Cliente> updateCliente(Long id, Cliente cliente);
    Mono<Cliente> getClienteById(Long id);
    Mono<Cliente> getClienteByNombre(String nombre);
    Mono<Void> deleteClienteById(Long id);
}
