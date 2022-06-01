package com.company.exeption;

public class AppBadRequestException extends GlobalException {
    public AppBadRequestException(String message) {
        super(message);
    }
}
