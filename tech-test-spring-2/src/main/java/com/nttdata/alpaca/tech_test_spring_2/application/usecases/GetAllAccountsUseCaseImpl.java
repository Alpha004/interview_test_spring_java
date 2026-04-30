package com.nttdata.alpaca.tech_test_spring_2.application.usecases;

import com.nttdata.alpaca.tech_test_spring_2.domain.models.Account;
import com.nttdata.alpaca.tech_test_spring_2.domain.ports.in.GetAllAccounts;
import com.nttdata.alpaca.tech_test_spring_2.domain.ports.out.AccountRepositoryPort;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class GetAllAccountsUseCaseImpl implements GetAllAccounts {

    private final AccountRepositoryPort accountRepositoryPort;

    public GetAllAccountsUseCaseImpl(AccountRepositoryPort accountRepositoryPort) {
        this.accountRepositoryPort = accountRepositoryPort;
    }

    @Override
    public Flux<Account> getAllAccounts(Pageable pageable) {
        return accountRepositoryPort.getAllAccounts(pageable);
    }
}
