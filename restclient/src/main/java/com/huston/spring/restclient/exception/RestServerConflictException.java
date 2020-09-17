package com.huston.spring.restclient.exception;

import org.springframework.http.HttpStatus;

import java.io.IOException;

public class RestServerConflictException extends IOException {

    public HttpStatus httpStatus;

    public RestServerConflictException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
