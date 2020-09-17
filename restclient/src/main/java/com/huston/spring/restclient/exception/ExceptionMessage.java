package com.huston.spring.restclient.exception;

import com.huston.spring.restclient.rest.SystemAwareCommunicationEntity;
import org.springframework.http.HttpStatus;

public class ExceptionMessage extends SystemAwareCommunicationEntity {


    private HttpStatus httpStatusCode;
    private String message;



}
