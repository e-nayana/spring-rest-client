package com.huston.spring.restclient.config.service;

import com.huston.spring.restclient.config.model.Configuration;


/**
 * Fetch configuration from configuration provider
 * implementation for
 */
public interface ConfigurationDBService {

    Configuration getConfiguration(String configIdentifier);
}
