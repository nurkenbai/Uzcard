package com.company.exeption;

public class BalanceNotFoundException extends GlobalException{
    public BalanceNotFoundException(String message) {
        super(message);
    }
}
