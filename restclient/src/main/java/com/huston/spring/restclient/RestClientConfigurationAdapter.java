package com.huston.spring.restclient;

import com.huston.spring.restclient.authentication.ApiAuthenticationProvider;
import com.huston.spring.restclient.authentication.ApiAuthenticationProviderManagerBuilder;
import com.huston.spring.restclient.config.service.ConfigurationDBService;
import com.huston.spring.restclient.config.service.DefaultConfigurationDBServiceImpl;
import com.huston.spring.restclient.rest.RestConsumerClient;
import com.huston.spring.restclient.rest.impl.RestConsumerClientImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.util.Assert;

import java.util.Arrays;

/**
 * Convenient way to configure RestConsumer client
 * @author Houston(Nayana)
 **/
public class RestClientConfigurationAdapter {

    RestConfigs restConfigs;

    @Bean
    @Qualifier("restConsumerClient")
    public RestConsumerClient getRestConsumerClient(){
        restConfigs = new RestConfigs();
        configure(restConfigs);
        preConstruct(restConfigs);
        return  new RestConsumerClientImpl(restConfigs.configurationDBService, restConfigs.authenticationManagerBuilder);
    }

    private final void preConstruct(RestConfigs restConfigs){

        Assert.notNull(restConfigs.configurationDBService, "Database configuration can't be null");
        Assert.notNull(restConfigs.authenticationManagerBuilder, "Authentication builder Configuration can't be null");
    }

    /**
     * This must be overrider to configure {@link ConfigurationDBService}
     * and {@link ApiAuthenticationProviderManagerBuilder}
     * Otherwise this uses default implementations
     * @param restConfigs
     */
    public void configure(RestConfigs restConfigs){}

    /**
     * RestConfigs for configure rest consumer client
     */
    public static class RestConfigs{
        ConfigurationDBService configurationDBService = new DefaultConfigurationDBServiceImpl();
        ApiAuthenticationProviderManagerBuilder authenticationManagerBuilder = new ApiAuthenticationProviderManagerBuilder();

        public RestConfigs setConfigurationDBService(ConfigurationDBService configurationDBService) {
            this.configurationDBService = configurationDBService;
            return this;
        }

        public RestConfigs setApiAuthenticationProvider(ApiAuthenticationProvider apiAuthenticationProvider){
            authenticationManagerBuilder.setAuthenticationProvider(apiAuthenticationProvider);
            return this;
        }

        public RestConfigs setApiAuthenticationProviders(ApiAuthenticationProvider... apiAuthenticationProviders){
            authenticationManagerBuilder.setAuthenticationProvider(Arrays.asList(apiAuthenticationProviders));
            return this;
        }

    }

}
