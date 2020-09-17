package com.spring.restclient.example.client;

import com.huston.spring.restclient.config.Exception.ConfigurationNotFoundException;
import com.spring.restclient.example.model.PayLoad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Uses rest services compounded with rest client
 * @author Houston(Nayana)
 **/
@RestController
@RequestMapping(value = "client")
public class ClientController {


    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping
    public String index() throws ConfigurationNotFoundException, IOException {

        return clientService.callServer(new PayLoad("A string"));
    }





}
