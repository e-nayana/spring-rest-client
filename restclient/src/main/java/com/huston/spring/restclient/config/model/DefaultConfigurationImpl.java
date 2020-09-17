package com.huston.spring.restclient.config.model;

/**
 * Default implementation of Configuration
 * @author Houston(Nayana)
 **/
public class DefaultConfigurationImpl implements Configuration {

    @Override
    public String getValue() {
        return "http://localhost:8080";
    }
}
