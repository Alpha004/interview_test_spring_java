package com.nttdata.alpaca.tech_test_spring_2.usecases;

import com.nttdata.alpaca.tech_test_spring_2.application.services.MovementService;
import com.nttdata.alpaca.tech_test_spring_2.config.mq.IMessageQueueProducer;
import com.nttdata.alpaca.tech_test_spring_2.config.utils.TipoMovement;
import com.nttdata.alpaca.tech_test_spring_2.domain.models.Account;
import com.nttdata.alpaca.tech_test_spring_2.domain.models.Movement;
import com.nttdata.alpaca.tech_test_spring_2.domain.ports.in.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class MovementServiceTestCase {

    private CreateMovement createMovement;
    private GetMovementById getMovementById;
    private GetMovementsByAccount getMovementsByAccount;
    private GetLastMovementByAccount getLastMovementByAccount;
    private CountMovementsByAccount countMovementsByAccount;
    private GetAccountByNumeroCuenta getAccountByNumeroCuenta;
    private IMessageQueueProducer mqProducer;
    private MovementService movementService;

    @BeforeEach
    void setUp() {
        createMovement = Mockito.mock(CreateMovement.class);
        getMovementById = Mockito.mock(GetMovementById.class);
        getMovementsByAccount = Mockito.mock(GetMovementsByAccount.class);
        getLastMovementByAccount = Mockito.mock(GetLastMovementByAccount.class);
        countMovementsByAccount = Mockito.mock(CountMovementsByAccount.class);
        getAccountByNumeroCuenta = Mockito.mock(GetAccountByNumeroCuenta.class);
        mqProducer = Mockito.mock(IMessageQueueProducer.class);

        movementService = new MovementService(
                createMovement,
                getMovementById,
                getMovementsByAccount,
                getLastMovementByAccount,
                countMovementsByAccount,
                getAccountByNumeroCuenta,
                mqProducer
        );

        ReflectionTestUtils.setField(movementService, "clienteTopic", "cliente-topic");
    }

    @Test
    void testSaveMovementWithPreviousMovement() {
        Movement lastMovement = new Movement();
        lastMovement.setSaldo(5000.0);
        lastMovement.setTipoCuenta("AHORRO");

        Account account = new Account();
        account.setClienteNombre("Jose Lema");

        Movement newMovement = new Movement();
        newMovement.setNumeroCuenta("1234567890");
        newMovement.setValor(500.0);
        newMovement.setTipoMovimiento(TipoMovement.CREDITO);
        newMovement.setSaldoInicial(5000.0);
        newMovement.setTipoCuenta("AHORRO");
        newMovement.setSaldo(5500.0);
        newMovement.setEstado(true);

        Mockito.when(getLastMovementByAccount.getLastMovementByAccount("1234567890"))
                .thenReturn(Mono.just(lastMovement));
        Mockito.when(getAccountByNumeroCuenta.getAccountByNumeroCuenta("1234567890"))
                .thenReturn(Mono.just(account));
        Mockito.when(createMovement.createMovement(Mockito.any(Movement.class)))
                .thenReturn(Mono.just(newMovement));

        StepVerifier.create(movementService.saveMovement(newMovement))
                .expectNextMatches(m ->
                        m.getNumeroCuenta().equals("1234567890") &&
                        m.getSaldo().equals(5500.0)
                )
                .verifyComplete();

        Mockito.verify(mqProducer).sendMovement(Mockito.any(), Mockito.eq("cliente-topic"));
    }

    @Test
    void testSaveMovementFirstMovement() {
        Account account = new Account();
        account.setSaldoInicial(1000.0);
        account.setTipo("AHORRO");
        account.setClienteNombre("Marianela Montalvo");

        Movement newMovement = new Movement();
        newMovement.setNumeroCuenta("0987654321");
        newMovement.setValor(200.0);
        newMovement.setTipoMovimiento(TipoMovement.CREDITO);
        newMovement.setSaldoInicial(1000.0);
        newMovement.setTipoCuenta("AHORRO");
        newMovement.setSaldo(1200.0);
        newMovement.setEstado(true);

        Mockito.when(getLastMovementByAccount.getLastMovementByAccount("0987654321"))
                .thenReturn(Mono.empty());
        Mockito.when(getAccountByNumeroCuenta.getAccountByNumeroCuenta("0987654321"))
                .thenReturn(Mono.just(account));
        Mockito.when(createMovement.createMovement(Mockito.any(Movement.class)))
                .thenReturn(Mono.just(newMovement));

        StepVerifier.create(movementService.saveMovement(newMovement))
                .expectNextMatches(m ->
                        m.getNumeroCuenta().equals("0987654321") &&
                        m.getSaldoInicial().equals(1000.0)
                )
                .verifyComplete();

        Mockito.verify(mqProducer).sendMovement(Mockito.any(), Mockito.eq("cliente-topic"));
    }

    @Test
    void testGetMovementsByAccount() {
        Movement movement1 = new Movement();
        movement1.setNumeroCuenta("1234567890");
        movement1.setValor(500.0);
        movement1.setTipoMovimiento(TipoMovement.CREDITO);
        movement1.setSaldo(5500.0);

        Movement movement2 = new Movement();
        movement2.setNumeroCuenta("1234567890");
        movement2.setValor(200.0);
        movement2.setTipoMovimiento(TipoMovement.DEBITO);
        movement2.setSaldo(5300.0);

        Mockito.when(getMovementsByAccount.getMovementsByAccount("1234567890"))
                .thenReturn(Flux.just(movement1, movement2));

        StepVerifier.create(movementService.getMovementsByAccount("1234567890"))
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void testGetMovementsByAccountEmpty() {
        Mockito.when(getMovementsByAccount.getMovementsByAccount("9999999999"))
                .thenReturn(Flux.empty());

        StepVerifier.create(movementService.getMovementsByAccount("9999999999"))
                .expectNextCount(0)
                .verifyComplete();
    }
}
