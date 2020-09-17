package com.huston.spring.restclient.rest;

import com.huston.spring.restclient.exception.RestServerConflictException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class RestConsumerErrorHandler implements ResponseErrorHandler {

    private ResponseErrorHandler errorHandler = new DefaultResponseErrorHandler();

    @Override
    public boolean hasError(ClientHttpResponse httpResponse)
            throws IOException {

        return errorHandler.hasError(httpResponse);
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse)
            throws IOException {

        HttpStatus statusCode = httpResponse.getStatusCode();
        StringBuilder inputStringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getBody(), StandardCharsets.UTF_8));
        String line = bufferedReader.readLine();
        while (line != null) {
            inputStringBuilder.append(line);
            line = bufferedReader.readLine();

        }
        String errorMessage = (inputStringBuilder.toString().length() > 300) ? "Your are seeing this message because the error message from third party system is too long to handle" : inputStringBuilder.toString();

        throw new RestServerConflictException(errorMessage, statusCode);


//        switch (statusCode) {
//            case CONFLICT:
//                throw new RestServerConflictException(errorMessage);
//            case NOT_FOUND:
//                throw new RestServerURINotFoundException();
//            case FORBIDDEN:
//                throw new SystemAuthFailedException(errorMessage);
//            default:
//                throw new IOException(errorMessage);
//
//        }
    }
}
