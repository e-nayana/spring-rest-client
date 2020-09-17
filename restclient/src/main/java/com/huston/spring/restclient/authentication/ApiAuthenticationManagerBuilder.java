package com.huston.spring.restclient.authentication;

import java.util.List;

/**
 * @author Houston(Nayana)
 **/

public interface ApiAuthenticationManagerBuilder {

    ApiAuthenticationManagerBuilder setAuthenticationProvider(ApiAuthenticationProvider apiAuthenticationProvider);
    ApiAuthenticationManagerBuilder setAuthenticationProvider(List<ApiAuthenticationProvider> apiAuthenticationProviders);
    ApiAuthenticationManager build();
}
