package com.huston.spring.restclient.authentication;

import com.huston.spring.restclient.authentication.model.RestApiAuthenticationHeader;

/**
 * @author Houston(Nayana)
 *
 **/
public interface ApiAuthenticationProvider {

    void setAuthentication(RestApiAuthenticationHeader headers);
    Boolean  isSupport(RestApiAuthenticationHeader headers);
}
