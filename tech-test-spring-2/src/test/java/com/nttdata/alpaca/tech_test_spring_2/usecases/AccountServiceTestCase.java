package com.nttdata.alpaca.tech_test_spring_2.usecases;

import com.nttdata.alpaca.tech_test_spring_2.config.exceptions.custom.NotFoundException;
import com.nttdata.alpaca.tech_test_spring_2.application.usecases.CreateAccountUseCaseImpl;
import com.nttdata.alpaca.tech_test_spring_2.application.usecases.GetAllAccountsUseCaseImpl;
import com.nttdata.alpaca.tech_test_spring_2.application.usecases.GetAccountByIdUseCaseImpl;
import com.nttdata.alpaca.tech_test_spring_2.application.usecases.UpdateAccountUseCaseImpl;
import com.nttdata.alpaca.tech_test_spring_2.application.usecases.DeleteAccountUseCaseImpl;
import com.nttdata.alpaca.tech_test_spring_2.domain.models.Account;
import com.nttdata.alpaca.tech_test_spring_2.domain.ports.out.AccountRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

class AccountServiceTestCase {

    private AccountRepositoryPort accountRepositoryPort;
    private CreateAccountUseCaseImpl createAccountUseCase;
    private GetAllAccountsUseCaseImpl getAllAccountsUseCase;
    private GetAccountByIdUseCaseImpl getAccountByIdUseCase;
    private UpdateAccountUseCaseImpl updateAccountUseCase;
    private DeleteAccountUseCaseImpl deleteAccountUseCase;

    @BeforeEach
    void setUp() {
        accountRepositoryPort = Mockito.mock(AccountRepositoryPort.class);
        createAccountUseCase = new CreateAccountUseCaseImpl(accountRepositoryPort);
        getAllAccountsUseCase = new GetAllAccountsUseCaseImpl(accountRepositoryPort);
        getAccountByIdUseCase = new GetAccountByIdUseCaseImpl(accountRepositoryPort);
        updateAccountUseCase = new UpdateAccountUseCaseImpl(accountRepositoryPort);
        deleteAccountUseCase = new DeleteAccountUseCaseImpl(accountRepositoryPort);
    }

    @Test
    void testCreateAccount() {
        Account account = new Account();
        account.setNumeroCuenta("1234567890");
        account.setTipo("AHORRO");
        account.setSaldoInicial(1000.0);
        account.setClienteNombre("Jose Lema");
        account.setEstado(true);

        when(accountRepositoryPort.saveAccount(account))
                .thenReturn(Mono.just(account));

        StepVerifier.create(createAccountUseCase.createAccount(account))
                .expectNextMatches(a ->
                        a.getNumeroCuenta().equals("1234567890") &&
                        a.getTipo().equals("AHORRO") &&
                        a.getSaldoInicial().equals(1000.0)
                )
                .verifyComplete();
    }

    @Test
    void testGetAllAccounts() {
        Pageable pageable = PageRequest.of(0, 10);

        Account account1 = new Account();
        account1.setNumeroCuenta("1234567890");
        account1.setTipo("AHORRO");
        account1.setSaldoInicial(1000.0);

        Account account2 = new Account();
        account2.setNumeroCuenta("0987654321");
        account2.setTipo("CORRIENTE");
        account2.setSaldoInicial(5000.0);

        Mockito.when(accountRepositoryPort.getAllAccounts(pageable))
                .thenReturn(Flux.just(account1, account2));

        StepVerifier.create(getAllAccountsUseCase.getAllAccounts(pageable))
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void testGetAllAccountsEmpty() {
        Pageable pageable = PageRequest.of(0, 10);

        Mockito.when(accountRepositoryPort.getAllAccounts(pageable))
                .thenReturn(Flux.empty());

        StepVerifier.create(getAllAccountsUseCase.getAllAccounts(pageable))
                .expectNextCount(0)
                .verifyComplete();
    }

    @Test
    void testGetAccountById() {
        Account account = new Account();
        account.setId(1L);
        account.setNumeroCuenta("1234567890");
        account.setTipo("AHORRO");
        account.setSaldoInicial(1000.0);

        Mockito.when(accountRepositoryPort.getAccountById(1L))
                .thenReturn(Mono.just(account));

        StepVerifier.create(getAccountByIdUseCase.getAccountById(1L))
                .expectNextMatches(a ->
                        a.getId().equals(1L) &&
                        a.getNumeroCuenta().equals("1234567890")
                )
                .verifyComplete();
    }

    @Test
    void testGetAccountByIdNotFound() {
        when(accountRepositoryPort.getAccountById(99L))
                .thenReturn(Mono.empty());

        StepVerifier.create(getAccountByIdUseCase.getAccountById(99L))
	        .expectErrorMatches(throwable -> throwable instanceof NotFoundException &&
	                throwable.getMessage().equals("Account not found with id: " + 99L))
	        .verify();
    }

    @Test
    void testUpdateAccount() {
        Account existingAccount = new Account();
        existingAccount.setId(1L);
        existingAccount.setNumeroCuenta("1234567890");
        existingAccount.setTipo("AHORRO");
        existingAccount.setSaldoInicial(1000.0);
        existingAccount.setClienteNombre("Jose Lema");
        existingAccount.setEstado(true);

        Account updatedAccount = new Account();
        updatedAccount.setTipo("CORRIENTE");
        updatedAccount.setSaldoInicial(2000.0);

        Account resultAccount = new Account();
        resultAccount.setId(1L);
        resultAccount.setNumeroCuenta("1234567890");
        resultAccount.setTipo("CORRIENTE");
        resultAccount.setSaldoInicial(2000.0);
        resultAccount.setClienteNombre("Jose Lema");
        resultAccount.setEstado(true);
        
        when(accountRepositoryPort.getAccountById(1L))
        .thenReturn(Mono.just(existingAccount));

        when(accountRepositoryPort.updateAccount(1L, updatedAccount))
                .thenReturn(Mono.just(resultAccount));

        StepVerifier.create(updateAccountUseCase.updateAccount(1L, updatedAccount))
                .expectNextMatches(a ->
                        a.getId().equals(1L) &&
                        a.getTipo().equals("CORRIENTE") &&
                        a.getSaldoInicial().equals(2000.0)
                )
                .verifyComplete();
    }

    @Test
    void testDeleteAccount() {
        Mockito.when(accountRepositoryPort.deleteAccountById(1L))
                .thenReturn(Mono.empty());

        StepVerifier.create(deleteAccountUseCase.deleteAccountById(1L))
                .verifyComplete();

        Mockito.verify(accountRepositoryPort).deleteAccountById(1L);
    }
}
