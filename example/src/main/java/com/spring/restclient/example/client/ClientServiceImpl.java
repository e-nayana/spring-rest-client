package com.spring.restclient.example.client;

import com.huston.spring.restclient.SYSTEM;
import com.huston.spring.restclient.config.Exception.ConfigurationNotFoundException;
import com.huston.spring.restclient.rest.RestConsumerClient;
import com.spring.restclient.example.model.PayLoad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author Houston(Nayana)
 **/

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    RestConsumerClient restConsumerClient;

    @Override
    public String callServer(PayLoad payLoad) throws ConfigurationNotFoundException, IOException {
        return restConsumerClient.primitivePost(true, SYSTEM.INVOICEMANGER, "/server", payLoad, null);
    }
}
