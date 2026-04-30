package com.nttdata.alpaca.tech_test_spring_2.infraestructure.repositories;

import com.nttdata.alpaca.tech_test_spring_2.infraestructure.entities.AccountEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface IAccountRepository extends ReactiveCrudRepository<AccountEntity, Long> {

    @Query("SELECT * FROM Accounts LIMIT :limit OFFSET :offset")
    Flux<AccountEntity> findAllPaginated(int limit, int offset);

    Mono<AccountEntity> findByNumeroCuenta(String numeroCuenta);
}
