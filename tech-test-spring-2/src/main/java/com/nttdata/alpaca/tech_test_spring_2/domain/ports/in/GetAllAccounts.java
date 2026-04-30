package com.nttdata.alpaca.tech_test_spring_2.domain.ports.in;

import org.springframework.data.domain.Pageable;
import com.nttdata.alpaca.tech_test_spring_2.domain.models.Account;
import reactor.core.publisher.Flux;

public interface GetAllAccounts {
    Flux<Account> getAllAccounts(Pageable pageable);
}
