package com.huston.spring.restclient.authentication;

import com.huston.spring.restclient.authentication.model.RestApiAuthenticationHeader;
import org.springframework.http.HttpHeaders;

/**
 * @author Houston(Nayana)
 **/

public interface ApiAuthenticationAware {

    void setAuthentication(RestApiAuthenticationHeader headers);
    ApiAuthenticationManager getAuthenticationManager();

}
