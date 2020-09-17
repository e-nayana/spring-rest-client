package com.spring.restclient.example.client;

import com.huston.spring.restclient.config.Exception.ConfigurationNotFoundException;
import com.spring.restclient.example.model.PayLoad;

import java.io.IOException;

/**
 * @author Houston(Nayana)
 **/

public interface ClientService {
    String callServer(PayLoad payLoad) throws ConfigurationNotFoundException, IOException;
}
