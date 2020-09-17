package com.huston.spring.restclient.exception;

import java.io.IOException;

public class RestServerURINotFoundException extends IOException {

    private final static String RESTSERVERURINOTFOUND = "rest template server uri not found";

    public RestServerURINotFoundException() {
        super(RESTSERVERURINOTFOUND);
    }
}
