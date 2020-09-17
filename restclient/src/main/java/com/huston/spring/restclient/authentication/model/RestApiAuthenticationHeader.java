package com.huston.spring.restclient.authentication.model;

import org.springframework.http.HttpHeaders;

/**
 * @author Houston(Nayana)
 **/

public class RestApiAuthenticationHeader {

    private HttpHeaders httpHeaders = new HttpHeaders();

    public HttpHeaders getHttpHeaders() {
        return httpHeaders;
    }

    public void setHttpHeaders(HttpHeaders httpHeaders) {
        this.httpHeaders = httpHeaders;
    }

    public void setHttpHeader(String key, String value){
        httpHeaders.set(key, value);
    }

    public Object getHttpHeader(String key){
        return httpHeaders.get(key);
    }
}
