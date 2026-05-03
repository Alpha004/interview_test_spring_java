package com.nttdata.alpaca.tech_test_spring_2.application.services;

import com.nttdata.alpaca.tech_test_spring_2.domain.models.Account;
import com.nttdata.alpaca.tech_test_spring_2.domain.ports.in.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountService {

    private final CreateAccount createAccount;
    private final GetAllAccounts getAllAccounts;
    private final GetAccountById getAccountById;
    private final GetAccountByNumeroCuenta getAccountByNumeroCuenta;
    private final UpdateAccount updateAccount;
    private final DeleteAccount deleteAccount;

    public AccountService(CreateAccount createAccount, GetAllAccounts getAllAccounts, GetAccountById getAccountById, GetAccountByNumeroCuenta getAccountByNumeroCuenta, UpdateAccount updateAccount, DeleteAccount deleteAccount) {
        this.createAccount = createAccount;
        this.getAllAccounts = getAllAccounts;
        this.getAccountById = getAccountById;
        this.getAccountByNumeroCuenta = getAccountByNumeroCuenta;
        this.updateAccount = updateAccount;
        this.deleteAccount = deleteAccount;
    }

    public Flux<Account> getAccounts(Pageable pageable) {
        return getAllAccounts.getAllAccounts(pageable);
    }

    public Mono<Account> saveAccount(Account account) {
    	account.setEstado(true);
        return createAccount.createAccount(account);
    }

    public Mono<Account> getAccountById(Long id) {
        return getAccountById.getAccountById(id);
    }

    public Mono<Account> getAccountByNumeroCuenta(String numeroCuenta) {
        return getAccountByNumeroCuenta.getAccountByNumeroCuenta(numeroCuenta);
    }

    public Mono<Account> updateAccount(Long id, Account account) {
        return updateAccount.updateAccount(id, account);
    }

    public Mono<Void> deleteAccount(Long id) {
        return deleteAccount.deleteAccountById(id);
    }
}
