package com.program.practice.bank.account.controller;

import com.program.practice.bank.account.exceptions.AccountAlreadyExistingException;
import com.program.practice.bank.account.model.AccountBlances;
import com.program.practice.bank.account.model.AccountDetails;
import com.program.practice.bank.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@RestController
@RequestMapping(path="/accounts")
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping("/open")
    public Mono<AccountDetails> processAccountOpening(@RequestBody AccountDetails accountDetails) {
      try{
        return accountService.saveAccount(accountDetails);
      }catch (AccountAlreadyExistingException exception){
          return Mono.just(AccountDetails.builder().message(exception.getMessage()).build());
      }
    }
    @PutMapping("/daily-interest")
    public Mono<AccountBlances> processAccountEndOfDayBalances(@RequestBody AccountBlances accountBlances) {
        return accountService.calculateDailyAccruedInterest(accountBlances);
    }

    @PutMapping("/monthly-interest")
    public Mono<AccountBlances> calculateMonthlyInterest(@RequestBody AccountBlances accountBlances) {
        return accountService.calculateMonthlyInterest(accountBlances); //accountService.saveAccount(accountDetails);
    }
}
