package com.huston.spring.restclient.authentication;

import com.huston.spring.restclient.authentication.model.RestApiAuthenticationHeader;

/**
 * @author Houston(Nayana)
 **/

public class ApiAuthenticationManagerDelegator implements ApiAuthenticationManager {

    private ApiAuthenticationManager delegate;
    private ApiAuthenticationManagerBuilder apiAuthenticationManagerBuilder;

    public ApiAuthenticationManagerDelegator(ApiAuthenticationManagerBuilder apiAuthenticationManagerBuilder) {
        this.apiAuthenticationManagerBuilder = apiAuthenticationManagerBuilder;
    }

    /**
     * Could be executed without a builder
     * But a builder{@link ApiAuthenticationProviderManagerBuilder} provides flexibility to customize the implementation of {@link ApiAuthenticationManager}
     * @param headers
     */
    @Override
    public void setAuthentication(RestApiAuthenticationHeader headers) {

        if(delegate == null){
            delegate = apiAuthenticationManagerBuilder.build();
        }
        else {
        }

        delegate.setAuthentication(headers);
    }

    public ApiAuthenticationManager getDelegate() {
        return delegate;
    }

    public void setDelegate(ApiAuthenticationManager delegate) {
        this.delegate = delegate;
    }

    public ApiAuthenticationManagerBuilder getApiAuthenticationManagerBuilder() {
        return apiAuthenticationManagerBuilder;
    }

    public void setApiAuthenticationManagerBuilder(ApiAuthenticationProviderManagerBuilder apiAuthenticationManagerBuilder) {
        this.apiAuthenticationManagerBuilder = apiAuthenticationManagerBuilder;
    }
}
