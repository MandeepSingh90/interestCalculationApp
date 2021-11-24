package com.program.practice.bank.account.service;

import com.program.practice.bank.account.exceptions.AccountAlreadyExistingException;
import com.program.practice.bank.account.model.AccountBlances;
import com.program.practice.bank.account.model.AccountDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface AccountService {

    public Mono<AccountDetails> saveAccount(AccountDetails accountDetails) throws AccountAlreadyExistingException;

    public Mono<AccountBlances> calculateMonthlyInterest(@RequestBody AccountBlances accountBlances);

    public Mono<AccountBlances> calculateDailyAccruedInterest(AccountBlances accountBlances);

}
