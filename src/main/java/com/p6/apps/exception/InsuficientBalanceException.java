package com.p6.apps.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class InsuficientBalanceException extends RuntimeException {

    public InsuficientBalanceException(String s) {
        super(s);
    }
}