package com.nttdata.alpaca.tech_test_spring.controller;

import com.nttdata.alpaca.tech_test_spring.application.services.ClienteService;
import com.nttdata.alpaca.tech_test_spring.config.exceptions.custom.NotFoundException;
import com.nttdata.alpaca.tech_test_spring.domain.models.Cliente;
import com.nttdata.alpaca.tech_test_spring.infraestructure.controller.ClienteRestController;
import com.nttdata.alpaca.tech_test_spring.infraestructure.dto.ClienteRequest;
import com.nttdata.alpaca.tech_test_spring.infraestructure.dto.ClienteResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteControllerTestCase {

    @Mock
    private ClienteService clienteService;

    private ClienteRestController clienteRestController;

    @BeforeEach
    void setUp() {
        clienteRestController = new ClienteRestController(clienteService);
    }

    @Test
    void retornarTodosLosClientes() {
        Cliente cliente1 = new Cliente("Juan", "Masculino", "12345678", "Dir1", "912345678", "12345Pso44%", true);
        Cliente cliente2 = new Cliente("Maria", "Femenino", "87654321", "Dir2", "987654321", "12345Pso42%", true);

        when(clienteService.getClients(any(org.springframework.data.domain.Pageable.class)))
                .thenReturn(Flux.fromIterable(Arrays.asList(cliente1, cliente2)));

        StepVerifier.create(clienteRestController.getListOfClients(0, 10))
                .expectNextMatches(response -> response.getStatusCode().is2xxSuccessful() 
                        && response.getBody() != null 
                        && response.getBody().size() == 2)
                .verifyComplete();
    }

    @Test
    void crearClienteSinErrores() {
        ClienteRequest request = new ClienteRequest("Juan", "Masculino", "12345678", "Dir1", "912345678", "12345Pso44%", null);
        Cliente clienteGuardado = new Cliente("Juan", "Masculino", "12345678", "Dir1", "912345678", "12345Pso44%", true);

        when(clienteService.saveCliente(any(Cliente.class)))
                .thenReturn(Mono.just(clienteGuardado));

        StepVerifier.create(clienteRestController.saveNewClient(request))
                .expectNextMatches(response -> response.getStatusCode().is2xxSuccessful())
                .verifyComplete();
    }

    @Test
    void actualizarClienteCorrecto() {
        Long id = 1L;
        ClienteRequest request = new ClienteRequest("Juan Actualizado", "Masculino", "12345678", "DirActualizada", "999999999", "12345Pso42%", true);
        Cliente clienteActualizado = new Cliente("Juan Actualizado", "Masculino", "12345678", "DirActualizada", "999999999", "12345Pso42%", true);

        when(clienteService.updateCliente(any(Cliente.class), anyLong()))
                .thenReturn(Mono.just(clienteActualizado));

        StepVerifier.create(clienteRestController.updateExistingClientById(request, id))
                .expectNextMatches(response -> response.getStatusCode().equals(HttpStatus.ACCEPTED))
                .verifyComplete();
    }

    @Test
    void obtenerClientePorIdCorrecto() {
        Long id = 1L;
        Cliente cliente = new Cliente("Juan", "Masculino", "12345678", "Dir1", "912345678", "12345Pso44%", true);

        when(clienteService.getClienteById(id))
                .thenReturn(Mono.just(cliente));

        StepVerifier.create(clienteRestController.getClientById(id))
                .expectNextMatches(response -> response.getStatusCode().equals(HttpStatus.OK))
                .verifyComplete();
    }

    @Test
    void obtenerClientePorIdIncorrecto() {
        Long id = 165L;

        when(clienteService.getClienteById(id))
                .thenReturn(Mono.error(new NotFoundException("Cliente no encontrado con id: " + id)));

        StepVerifier.create(clienteRestController.getClientById(id))
                .expectErrorMatches(throwable -> throwable instanceof NotFoundException)
                .verify();
    }

    @Test
    void eliminarClienteCorrecto() {
        Long id = 1L;

        when(clienteService.deleteCliente(id))
                .thenReturn(Mono.empty());

        StepVerifier.create(clienteRestController.deleteClientById(id))
                .expectNextMatches(response -> response.getStatusCode().equals(HttpStatus.NO_CONTENT))
                .verifyComplete();
    }

    @Test
    void eliminarClienteQueNoExiste() {
        Long id = 674L;

        when(clienteService.deleteCliente(id))
                .thenReturn(Mono.error(new NotFoundException("Cliente no encontrado con id: " + id)));

        StepVerifier.create(clienteRestController.deleteClientById(id))
                .expectErrorMatches(throwable -> throwable instanceof NotFoundException)
                .verify();
    }
}
