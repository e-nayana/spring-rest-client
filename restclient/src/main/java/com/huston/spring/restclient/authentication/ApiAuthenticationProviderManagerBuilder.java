package com.huston.spring.restclient.authentication;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Houston(Nayana)
 **/

public class ApiAuthenticationProviderManagerBuilder implements ApiAuthenticationManagerBuilder{

    private List<ApiAuthenticationProvider> authenticationProviders = new ArrayList<>();

    @Override
    public ApiAuthenticationManagerBuilder setAuthenticationProvider(ApiAuthenticationProvider apiAuthenticationProvider){
        authenticationProviders.add(apiAuthenticationProvider);
        return this;
    }

    @Override
    public ApiAuthenticationManagerBuilder setAuthenticationProvider(List<ApiAuthenticationProvider> apiAuthenticationProviders){
        authenticationProviders = apiAuthenticationProviders;
        return this;
    }

    @Override
    public ApiAuthenticationManager build(){
        return new ApiAuthenticationProviderManager(authenticationProviders);
    }
}
