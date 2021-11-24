package com.program.practice.bank.account.exceptions;

public class AccountAlreadyExistingException extends Exception{
    String message;
    Integer errorCode;

    public AccountAlreadyExistingException(String message,Integer errorCode){
          super(message);
          this.errorCode = errorCode;
    }
}
