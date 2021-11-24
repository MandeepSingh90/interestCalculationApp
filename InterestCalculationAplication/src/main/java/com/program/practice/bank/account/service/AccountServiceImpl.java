package com.program.practice.bank.account.service;

import com.program.practice.bank.account.entity.AccountDetailsEntity;
import com.program.practice.bank.account.exceptions.AccountAlreadyExistingException;
import com.program.practice.bank.account.model.AccountBlances;
import com.program.practice.bank.account.model.AccountDetails;
import com.program.practice.bank.account.repositories.AccountRepository;
import com.program.practice.bank.account.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService{
    @Autowired
    AccountRepository accountRepository;
     @Override
    public Mono<AccountDetails> saveAccount(AccountDetails accountDetails) throws AccountAlreadyExistingException{

         AccountDetailsEntity accountDetailsEntity = AccountDetailsEntity.builder().bsb(accountDetails.getBsb())
                                                    .identification(accountDetails.getIdentification()).openingDate(accountDetails.getOpeningDate()).updated_at(new Date()).build();
         String message = null;
         try {
             AccountDetailsEntity accountDetailsEntity1 = accountRepository.save(accountDetailsEntity);

        AccountDetails accountDetails1 = AccountDetails.builder().bsb(accountDetailsEntity.getBsb())
                .identification(accountDetailsEntity.getIdentification())
                .openingDate(accountDetailsEntity.getOpeningDate()).updatedAt(new Date()).message(message).build();

        System.out.println("account opening");

        return Mono.just(accountDetails1);
         }catch (Exception exception){
             log.error("exception Occured while opening account ,{}",exception);
            throw new AccountAlreadyExistingException(" account is already existing ",302);
         }
    }

    @Override
    public Mono<AccountBlances> calculateMonthlyInterest(@RequestBody AccountBlances accountBlances) {
        List<AccountDetailsEntity> accountDetailsEntities =  accountRepository.findAll();
        log.info("-----List size- {}",accountDetailsEntities.size());
        int rateOfInterst = Constants.MONTHELY_RATE_OF_INTEREST;
        int monthDays= Constants.MONTHS_DAYS;
        Date date= new Date();


      List<AccountDetails> monthlyInterest =    accountDetailsEntities.stream().map(accountDetailsEntity -> {
            double interest =   (rateOfInterst*accountDetailsEntity.getBalance())/100;
            double balance   = accountDetailsEntity.getBalance()+ interest;
            return AccountDetails.builder().bsb(accountDetailsEntity.getBsb()).monthlyInterest(interest)
                    .identification(accountDetailsEntity.getIdentification()).build();
        }).collect(Collectors.toList());
        accountDetailsEntities =  accountDetailsEntities.stream().filter(accountDetailsEntity ->date.getDay()-accountDetailsEntity.getUpdated_at().getDay() >= 30).map(accountDetailsEntity -> {
            double interest =   (rateOfInterst*accountDetailsEntity.getBalance())/100;
            double balance   = accountDetailsEntity.getBalance()+ interest;
            accountDetailsEntity.setBalance(balance);
            accountDetailsEntity.setUpdated_at(date);
            return accountDetailsEntity;
        }).collect(Collectors.toList());
         accountRepository.saveAll(accountDetailsEntities);

        AccountBlances accountBlances1 = new AccountBlances(new Date(),monthlyInterest);
        return  Mono.just(accountBlances1);
    }

    @Override
    public Mono<AccountBlances> calculateDailyAccruedInterest(AccountBlances accountBlances) {
       List<AccountDetailsEntity> accountDetailsEntities =  accountRepository.findAll();
       log.info("-accountDetailsEntities size- {}",accountDetailsEntities.size());
       int rateOfInterst = Constants.MONTHELY_RATE_OF_INTEREST;
       int monthDays= Constants.MONTHS_DAYS;
        Date date= new Date();
        accountDetailsEntities =  accountDetailsEntities.stream().map(accountDetailsEntity -> {
            double interest =   (rateOfInterst*accountDetailsEntity.getBalance())/monthDays;
            double balance   = accountDetailsEntity.getBalance()+ (interest * (date.getDay()-accountDetailsEntity.getUpdated_at().getDay()))/100;
            accountDetailsEntity.setBalance(balance);
            accountDetailsEntity.setUpdated_at(date);
            return accountDetailsEntity;
        }).collect(Collectors.toList());

    List<AccountDetails> accountDetails1 =    accountDetailsEntities.stream().map(accountDetailsEntity -> {
            return AccountDetails.builder().bsb(accountDetailsEntity.getBsb())
                    .identification(accountDetailsEntity.getIdentification())
                    .openingDate(accountDetailsEntity.getOpeningDate()).balance(accountDetailsEntity.getBalance()).updatedAt(new Date()).build();
        }).collect(Collectors.toList());
  AccountBlances accountBlances1 = new AccountBlances(new Date(),accountDetails1);
  return  Mono.just(accountBlances1);
    }
}
