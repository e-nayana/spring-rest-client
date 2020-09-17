package com.huston.spring.restclient.exception;

import java.io.IOException;

public class SystemAuthFailedException extends IOException
{

    public SystemAuthFailedException(String message){
        super(message);
    }

}
