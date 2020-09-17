package com.huston.spring.restclient.config.service;

import com.huston.spring.restclient.config.model.Configuration;
import com.huston.spring.restclient.config.model.DefaultConfigurationImpl;

/**
 * Default implementation of {@link ConfigurationDBService}
 * @author Houston(Nayana)
 **/
public class DefaultConfigurationDBServiceImpl implements ConfigurationDBService {
    @Override
    public Configuration getConfiguration(String configIdentifier) {
        return new DefaultConfigurationImpl();
    }
}
