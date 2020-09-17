package com.huston.spring.restclient.authentication;

import com.huston.spring.restclient.authentication.model.RestApiAuthenticationHeader;

/**
 * @author Houston(Nayana)
 **/

public interface ApiAuthenticationManager {

    void setAuthentication(RestApiAuthenticationHeader headers);
}
