package com.nttdata.alpaca.tech_test_spring_2.controller;

import com.nttdata.alpaca.tech_test_spring_2.application.mapper.AccountMapper;
import com.nttdata.alpaca.tech_test_spring_2.application.services.AccountService;
import com.nttdata.alpaca.tech_test_spring_2.domain.models.Account;
import com.nttdata.alpaca.tech_test_spring_2.infraestructure.dto.AccountRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.bean.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@WebFluxTest(AccountRestController.class)
class AccountControllerTestCase {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private AccountService accountService;

    @Test
    void testGetAllAccounts() {
        Account account = new Account();
        account.setNumeroCuenta("1234567890");
        account.setTipo("AHORRO");
        account.setSaldoInicial(1000.0);
        account.setEstado(true);
        account.setClienteNombre("Jose Lema");

        org.mockito.Mockito.when(accountService.getAccounts(org.mockito.any()))
                .thenReturn(Flux.just(account));

        webTestClient.get()
                .uri("/v1/api/accounts?page=0&size=10")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].numeroCuenta").isEqualTo("1234567890")
                .jsonPath("$[0].tipo").isEqualTo("AHORRO")
                .jsonPath("$[0].saldoInicial").isEqualTo(1000.0);
    }

    @Test
    void testGetAccountById() {
        Account account = new Account();
        account.setId(1L);
        account.setNumeroCuenta("1234567890");
        account.setTipo("AHORRO");
        account.setSaldoInicial(1000.0);
        account.setEstado(true);
        account.setClienteNombre("Jose Lema");

        org.mockito.Mockito.when(accountService.getAccountById(1L))
                .thenReturn(Mono.just(account));

        webTestClient.get()
                .uri("/v1/api/accounts/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.numeroCuenta").isEqualTo("1234567890")
                .jsonPath("$.tipo").isEqualTo("AHORRO");
    }

    @Test
    void testGetAccountByIdNotFound() {
        org.mockito.Mockito.when(accountService.getAccountById(99L))
                .thenReturn(Mono.empty());

        webTestClient.get()
                .uri("/v1/api/accounts/99")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testCreateAccount() {
        Account account = new Account();
        account.setNumeroCuenta("1234567890");
        account.setTipo("AHORRO");
        account.setSaldoInicial(1000.0);
        account.setEstado(true);
        account.setClienteNombre("Jose Lema");

        org.mockito.Mockito.when(accountService.saveAccount(org.mockito.any(Account.class)))
                .thenReturn(Mono.just(account));

        webTestClient.post()
                .uri("/v1/api/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new AccountRequest("1234567890", "AHORRO", 1000.0, true, "Jose Lema"))
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.numeroCuenta").isEqualTo("1234567890")
                .jsonPath("$.clienteNombre").isEqualTo("Jose Lema");
    }

    @Test
    void testUpdateAccount() {
        Account account = new Account();
        account.setId(1L);
        account.setNumeroCuenta("1234567890");
        account.setTipo("CORRIENTE");
        account.setSaldoInicial(2000.0);
        account.setEstado(true);
        account.setClienteNombre("Jose Lema");

        org.mockito.Mockito.when(accountService.updateAccount(org.mockito.eq(1L), org.mockito.any(Account.class)))
                .thenReturn(Mono.just(account));

        webTestClient.put()
                .uri("/v1/api/accounts/1")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new AccountRequest("1234567890", "CORRIENTE", 2000.0, true, "Jose Lema"))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.tipo").isEqualTo("CORRIENTE")
                .jsonPath("$.saldoInicial").isEqualTo(2000.0);
    }

    @Test
    void testDeleteAccount() {
        org.mockito.Mockito.when(accountService.deleteAccount(1L))
                .thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/v1/api/accounts/1")
                .exchange()
                .expectStatus().isNoContent();
    }
}
