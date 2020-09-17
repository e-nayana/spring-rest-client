package com.huston.spring.restclient.authentication;

import com.huston.spring.restclient.authentication.model.RestApiAuthenticationHeader;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Houston(Nayana)
 **/

public class ApiAuthenticationProviderManager implements ApiAuthenticationManager {

    private List<ApiAuthenticationProvider> authenticationProviders = Collections.emptyList();

    public ApiAuthenticationProviderManager(List<ApiAuthenticationProvider> authenticationProviders) {
        this.authenticationProviders = authenticationProviders;
//        postConstructCheck();
    }

    public ApiAuthenticationProviderManager(ApiAuthenticationProvider... authenticationProviders) {
        this.authenticationProviders = Arrays.asList(authenticationProviders);
        postConstructCheck();
    }

    @Override
    public void setAuthentication(RestApiAuthenticationHeader headers) {

        for (ApiAuthenticationProvider authenticationProvider:authenticationProviders)
        {
            if(authenticationProvider.isSupport(headers)){
                authenticationProvider.setAuthentication(headers);
            }
        }
    }

    private void postConstructCheck() throws IllegalArgumentException{
        if(authenticationProviders.isEmpty()){
            throw new IllegalArgumentException("authenticationProviders list is empty");
        }
    }
}
