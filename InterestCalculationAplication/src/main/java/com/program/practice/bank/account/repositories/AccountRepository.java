package com.program.practice.bank.account.repositories;

import com.program.practice.bank.account.entity.AccountDetailsEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface AccountRepository  extends JpaRepository<AccountDetailsEntity, Long> {

    AccountDetailsEntity save(final AccountDetailsEntity accountDetailsEntity);

    @Override
    List<AccountDetailsEntity> findAll();

    List<AccountDetailsEntity> findAllByIdentification(List<Long> identifications);
}
