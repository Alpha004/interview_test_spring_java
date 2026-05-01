package com.nttdata.alpaca.tech_test_spring_2.controller;

import com.nttdata.alpaca.tech_test_spring_2.application.services.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class AccountControllerTestCase {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private AccountService accountService;

    @Test
    void testGetAllAccounts() {
        org.mockito.Mockito.when(accountService.getAccounts(org.springframework.data.domain.Pageable.unpaged()))
                .thenReturn(Flux.empty());

        webTestClient.get().uri("/v1/api/accounts")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testGetAccountById() {
        org.mockito.Mockito.when(accountService.getAccountById(1L))
                .thenReturn(Mono.just(new com.nttdata.alpaca.tech_test_spring_2.domain.models.Account()));

        webTestClient.get().uri("/v1/api/accounts/1")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testCreateAccount() {
        org.mockito.Mockito.when(accountService.saveAccount(org.mockito.Mockito.any()))
                .thenReturn(Mono.just(new com.nttdata.alpaca.tech_test_spring_2.domain.models.Account()));

        webTestClient.post().uri("/v1/api/accounts")
                .bodyValue(new com.nttdata.alpaca.tech_test_spring_2.infraestructure.dto.AccountRequest())
                .exchange()
                .expectStatus().isCreated();
    }
}
