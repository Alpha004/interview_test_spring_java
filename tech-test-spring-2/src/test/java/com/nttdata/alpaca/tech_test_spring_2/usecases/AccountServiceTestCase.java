package com.nttdata.alpaca.tech_test_spring_2.usecases;

import com.nttdata.alpaca.tech_test_spring_2.application.usecases.CreateAccountUseCaseImpl;
import com.nttdata.alpaca.tech_test_spring_2.domain.models.Account;
import com.nttdata.alpaca.tech_test_spring_2.domain.ports.out.AccountRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class AccountServiceTestCase {

    private AccountRepositoryPort accountRepositoryPort;
    private CreateAccountUseCaseImpl createAccountUseCase;

    @BeforeEach
    void setUp() {
        accountRepositoryPort = Mockito.mock(AccountRepositoryPort.class);
        createAccountUseCase = new CreateAccountUseCaseImpl(accountRepositoryPort);
    }

    @Test
    void testCreateAccount() {
        Account account = new Account();
        Mockito.when(accountRepositoryPort.saveAccount(account))
                .thenReturn(Mono.just(account));

        StepVerifier.create(createAccountUseCase.createAccount(account))
                .expectNext(account)
                .verifyComplete();
    }
}
