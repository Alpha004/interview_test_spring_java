package com.nttdata.alpaca.tech_test_spring_2.infraestructure.controller;

import com.nttdata.alpaca.tech_test_spring_2.application.mapper.AccountMapper;
import com.nttdata.alpaca.tech_test_spring_2.application.services.AccountService;
import com.nttdata.alpaca.tech_test_spring_2.domain.models.Account;
import com.nttdata.alpaca.tech_test_spring_2.infraestructure.dto.AccountRequest;
import com.nttdata.alpaca.tech_test_spring_2.infraestructure.dto.AccountResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/api/accounts")
public class AccountRestController {

    private final AccountService accountService;

    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public Flux<AccountResponse> getAllAccounts(Pageable pageable) {
        return accountService.getAccounts(pageable)
                .map(AccountMapper::fromDomainToResponse);
    }

    @GetMapping("/{accountId}")
    public Mono<AccountResponse> getAccountById(@PathVariable Long accountId) {
        return accountService.getAccountById(accountId)
                .map(AccountMapper::fromDomainToResponse);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<AccountResponse> createAccount(@RequestBody AccountRequest request) {
        Account account = AccountMapper.fromRequestToDomain(request);
        return accountService.saveAccount(account)
                .map(AccountMapper::fromDomainToResponse);
    }

    @PutMapping("/{accountId}")
    public Mono<AccountResponse> updateAccount(@PathVariable Long accountId, @RequestBody AccountRequest request) {
        Account account = AccountMapper.fromRequestToDomain(request);
        return accountService.updateAccount(accountId, account)
                .map(AccountMapper::fromDomainToResponse);
    }

    @DeleteMapping("/{accountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteAccount(@PathVariable Long accountId) {
        return accountService.deleteAccount(accountId);
    }
}
