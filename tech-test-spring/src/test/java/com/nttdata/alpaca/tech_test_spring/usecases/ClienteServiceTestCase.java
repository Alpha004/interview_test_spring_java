package com.nttdata.alpaca.tech_test_spring.usecases;

import com.nttdata.alpaca.tech_test_spring.application.usecases.CreateNewClienteUseCaseImpl;
import com.nttdata.alpaca.tech_test_spring.application.usecases.DeleteClienteByIdUseCaseImpl;
import com.nttdata.alpaca.tech_test_spring.application.usecases.GetAllClientsUseCaseImpl;
import com.nttdata.alpaca.tech_test_spring.application.usecases.GetClienteByIdUseCaseImpl;
import com.nttdata.alpaca.tech_test_spring.application.usecases.UpdateClienteByIdUseCaseImpl;
import com.nttdata.alpaca.tech_test_spring.config.exceptions.custom.NotFoundException;
import com.nttdata.alpaca.tech_test_spring.domain.models.Cliente;
import com.nttdata.alpaca.tech_test_spring.domain.ports.out.ClienteRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTestCase {

    @Mock
    private ClienteRepositoryPort clienteRepositoryPort;

    private GetAllClientsUseCaseImpl getAllClientsUseCase;
    private CreateNewClienteUseCaseImpl createNewClienteUseCase;
    private GetClienteByIdUseCaseImpl getClienteByIdUseCase;
    private UpdateClienteByIdUseCaseImpl updateClienteByIdUseCase;
    private DeleteClienteByIdUseCaseImpl deleteClienteByIdUseCase;

    @BeforeEach
    void setUp() {
        getAllClientsUseCase = new GetAllClientsUseCaseImpl(clienteRepositoryPort);
        createNewClienteUseCase = new CreateNewClienteUseCaseImpl(clienteRepositoryPort);
        getClienteByIdUseCase = new GetClienteByIdUseCaseImpl(clienteRepositoryPort);
        updateClienteByIdUseCase = new UpdateClienteByIdUseCaseImpl(clienteRepositoryPort);
        deleteClienteByIdUseCase = new DeleteClienteByIdUseCaseImpl(clienteRepositoryPort);
    }

    @Test
    void retornarTodosLosClientes() {
        Cliente cliente1 = new Cliente("Juan", "Masculino", "12345678", "Dir1", "912345678", "Contrasenia1$", true);
        Cliente cliente2 = new Cliente("Maria", "Femenino", "87654321", "Dir2", "987654321", "UnPassword2$", true);

        when(clienteRepositoryPort.getAllClientes(any(Pageable.class)))
                .thenReturn(Flux.fromIterable(List.of(cliente1, cliente2)));

        StepVerifier.create(getAllClientsUseCase.getAllClientes(Pageable.unpaged()))
                .expectNext(cliente1)
                .expectNext(cliente2)
                .verifyComplete();
    }

    @Test
    void crearClienteSinErrores() {
        Cliente cliente = new Cliente("Juan", "Masculino", "12345678", "Dir1", "912345678", "Password1#", null);
        Cliente clienteGuardado = new Cliente("Juan", "Masculino", "12345678", "Dir1", "912345678", "Password1#", true);

        when(clienteRepositoryPort.saveCliente(any(Cliente.class)))
                .thenReturn(Mono.just(clienteGuardado));

        StepVerifier.create(createNewClienteUseCase.saveCliente(cliente))
                .expectNextMatches(c -> c.getNombre().equals("Juan") && Boolean.TRUE.equals(c.getEstado()))
                .verifyComplete();
    }

    @Test
    void actualizarClienteCorrecto() {
        Long id = 1L;
        Cliente clienteExistente = new Cliente("Juan", "Masculino", "12345678", "Dir1", "912345678", "12345Pso44%", true);
        Cliente clienteActualizado = new Cliente("Juan Actualizado", "Masculino", "12345678", "DirActualizada", "999999999", "1New2Pso44%", true);

        when(clienteRepositoryPort.getClienteById(id))
                .thenReturn(Mono.just(clienteExistente));
        when(clienteRepositoryPort.updateCliente(anyLong(), any(Cliente.class)))
                .thenReturn(Mono.just(clienteActualizado));

        StepVerifier.create(updateClienteByIdUseCase.updateCliente(id, clienteActualizado))
                .expectNextMatches(c -> c.getNombre().equals("Juan Actualizado"))
                .verifyComplete();
    }

    @Test
    void actualizarClienteQueNoExiste() {
        Long id = 165L;
        Cliente cliente = new Cliente("Juan", "Masculino", "12345678", "Dir1", "912345678", "12345Pso44%", true);

        when(clienteRepositoryPort.getClienteById(id))
                .thenReturn(Mono.empty());

        StepVerifier.create(updateClienteByIdUseCase.updateCliente(id, cliente))
                .expectComplete()
                .verify();
    }

    @Test
    void obtenerClientePorIdCorrecto() {
        Long id = 1L;
        Cliente cliente = new Cliente("Juan", "Masculino", "12345678", "Dir1", "912345678", "12345Pso44%", true);

        when(clienteRepositoryPort.getClienteById(id))
                .thenReturn(Mono.just(cliente));

        StepVerifier.create(getClienteByIdUseCase.getClienteById(id))
                .expectNextMatches(c -> c.getNombre().equals("Juan"))
                .verifyComplete();
    }

    @Test
    void obtenerClientePorIdIncorrecto() {
        Long id = 215L;

        when(clienteRepositoryPort.getClienteById(id))
                .thenReturn(Mono.error(new NotFoundException("Cliente no encontrado con id: " + id)));

        StepVerifier.create(getClienteByIdUseCase.getClienteById(id))
                .expectErrorMatches(throwable -> throwable instanceof NotFoundException &&
                        throwable.getMessage().equals("Cliente no encontrado con id: " + id))
                .verify();
    }

    @Test
    void eliminarClienteCorrecto() {
        Long id = 1L;

        when(clienteRepositoryPort.deleteClienteById(id))
                .thenReturn(Mono.empty());

        StepVerifier.create(deleteClienteByIdUseCase.deleteClienteById(id))
                .verifyComplete();
    }

    @Test
    void eliminarClienteQueNoExiste() {
        Long id = 678L;

        when(clienteRepositoryPort.deleteClienteById(id))
                .thenReturn(Mono.error(new NotFoundException("Cliente no encontrado con id: " + id)));

        StepVerifier.create(deleteClienteByIdUseCase.deleteClienteById(id))
                .expectErrorMatches(throwable -> throwable instanceof NotFoundException &&
                        throwable.getMessage().equals("Cliente no encontrado con id: " + id))
                .verify();
    }
}
