package com.nttdata.alpaca.tech_test_spring_2.controller;

import com.nttdata.alpaca.tech_test_spring_2.application.services.MovementService;
import com.nttdata.alpaca.tech_test_spring_2.config.utils.TipoMovement;
import com.nttdata.alpaca.tech_test_spring_2.domain.models.Movement;
import com.nttdata.alpaca.tech_test_spring_2.infraestructure.dto.MovementRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.bean.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebFluxTest(MovementRestController.class)
class MovementControllerTestCase {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private MovementService movementService;

    @Test
    void testGetMovementsByAccount() {
        Movement movement1 = new Movement();
        movement1.setNumeroCuenta("1234567890");
        movement1.setTipoCuenta("AHORRO");
        movement1.setSaldoInicial(1000.0);
        movement1.setValor(500.0);
        movement1.setTipoMovimiento(TipoMovement.CREDITO);
        movement1.setSaldo(1500.0);

        Movement movement2 = new Movement();
        movement2.setNumeroCuenta("1234567890");
        movement2.setTipoCuenta("AHORRO");
        movement2.setSaldoInicial(1500.0);
        movement2.setValor(200.0);
        movement2.setTipoMovimiento(TipoMovement.DEBITO);
        movement2.setSaldo(1300.0);

        org.mockito.Mockito.when(movementService.getMovementsByAccount("1234567890"))
                .thenReturn(Flux.just(movement1, movement2));

        webTestClient.get()
                .uri("/v1/api/movements/account/1234567890")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].numeroCuenta").isEqualTo("1234567890")
                .jsonPath("$[0].valor").isEqualTo(500.0)
                .jsonPath("$[1].valor").isEqualTo(200.0);
    }

    @Test
    void testGetMovementById() {
        Movement movement = new Movement();
        movement.setId(1L);
        movement.setNumeroCuenta("1234567890");
        movement.setTipoCuenta("AHORRO");
        movement.setSaldoInicial(1000.0);
        movement.setValor(500.0);
        movement.setTipoMovimiento(TipoMovement.CREDITO);
        movement.setSaldo(1500.0);

        org.mockito.Mockito.when(movementService.getMovementById(1L))
                .thenReturn(Mono.just(movement));

        webTestClient.get()
                .uri("/v1/api/movements/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.numeroCuenta").isEqualTo("1234567890")
                .jsonPath("$.saldo").isEqualTo(1500.0);
    }

    @Test
    void testGetLastMovementByAccount() {
        Movement movement = new Movement();
        movement.setSaldo(1300.0);
        movement.setTipoCuenta("AHORRO");

        org.mockito.Mockito.when(movementService.getLastMovementByAccount("1234567890"))
                .thenReturn(Mono.just(movement));

        webTestClient.get()
                .uri("/v1/api/movements/account/1234567890/last")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.saldo").isEqualTo(1300.0)
                .jsonPath("$.tipoCuenta").isEqualTo("AHORRO");
    }

    @Test
    void testCreateMovement() {
        Movement movement = new Movement();
        movement.setNumeroCuenta("1234567890");
        movement.setTipoCuenta("AHORRO");
        movement.setSaldoInicial(1000.0);
        movement.setValor(500.0);
        movement.setTipoMovimiento(TipoMovement.CREDITO);
        movement.setSaldo(1500.0);

        org.mockito.Mockito.when(movementService.saveMovement(org.mockito.any(Movement.class)))
                .thenReturn(Mono.just(movement));

        webTestClient.post()
                .uri("/v1/api/movements")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new MovementRequest("1234567890", 500.0, "CREDITO"))
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.numeroCuenta").isEqualTo("1234567890")
                .jsonPath("$.valor").isEqualTo(500.0)
                .jsonPath("$.saldo").isEqualTo(1500.0);
    }
}
