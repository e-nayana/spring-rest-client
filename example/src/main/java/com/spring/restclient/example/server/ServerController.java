package com.spring.restclient.example.server;

import com.spring.restclient.example.model.PayLoad;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Act as a server for testing
 *
 * @author Houston(Nayana)
 **/
@RestController
@RequestMapping(value = "server")
public class ServerController {

    @RequestMapping(method = RequestMethod.POST)
    public String index(@RequestBody PayLoad payLoad){
        return "Willy Wonka";
    }
}
