package com.huston.spring.restclient.rest;

import com.huston.spring.restclient.SYSTEM;
import com.huston.spring.restclient.config.Exception.ConfigurationNotFoundException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface RestConsumerClient {

    /**
     * special for authenticate from system
     * @param exceptionEnable
     * @param system
     * @param endPoint
     * @param body
     * @param responseEntityType
     * @param <B> body
     * @param <R> result
     * @return
     * @throws ConfigurationNotFoundException
     * @throws IOException
     */
    <B extends SystemAwareCommunicationEntity, R extends SystemAwareCommunicationEntity> R authPost(Boolean exceptionEnable, SYSTEM system, String endPoint, B body, Class<R> responseEntityType) throws ConfigurationNotFoundException, IOException;

    /**
     * return simple obejct from GET
     * @param system
     * @param endPoint
     * @param responseEntityType
     * @param queryParams
     * @param <T>
     * @return
     * @throws ConfigurationNotFoundException
     * @throws IOException
     */
    <T extends SystemAwareCommunicationEntity> T getObject(Boolean exceptionEnable, SYSTEM system, String endPoint, Class<T> responseEntityType, @Nullable HashMap<String, String> queryParams, String... pathParameters) throws ConfigurationNotFoundException, IOException;

    /**
     * GET with BODY
     * Event if this is get this take object as the body
     * return simple object
     * @param exceptionEnable
     * @param system
     * @param endPoint
     * @param requestEntity
     * @param responseEntityType
     * @param queryParams
     * @param <T>
     * @return
     * @throws ConfigurationNotFoundException
     * @throws IOException
     */
    <B, R> R getObject(Boolean exceptionEnable, SYSTEM system, String endPoint, B requestEntity, Class<R> responseEntityType, HashMap<String, String> queryParams, String... pathParameters) throws ConfigurationNotFoundException, IOException;

    /**
     * get list of object
     * @param system
     * @param endPoint
     * @param parameterizedTypeReference
     * @param queryParams
     * @param <T>
     * @return
     * @throws ConfigurationNotFoundException
     * @throws IOException
     */
    <T extends SystemAwareCommunicationEntity> List<T> getList(Boolean exceptionEnable, SYSTEM system, String endPoint, ParameterizedTypeReference parameterizedTypeReference, @Nullable HashMap<String, String> queryParams) throws ConfigurationNotFoundException, IOException;

    /**
     * get map of key->object GET
     * @param system
     * @param endPoint
     * @param parameterizedTypeReference
     * @param queryParams
     * @param <T>
     * @return
     * @throws ConfigurationNotFoundException
     * @throws IOException
     */
    <T extends SystemAwareCommunicationEntity> HashMap<String, T> getMap(Boolean exceptionEnable, SYSTEM system, String endPoint, ParameterizedTypeReference parameterizedTypeReference, @Nullable HashMap<String, String> queryParams) throws ConfigurationNotFoundException, IOException;

    /**
     * get object by POST
     * @param system
     * @param endPoint
     * @param requestEntity
     * @param responseEntityType
     * @param queryParams
     * @param <T>
     * @return
     * @throws ConfigurationNotFoundException
     * @throws IOException
     */
    <T> T post(Boolean exceptionEnable, SYSTEM system, String endPoint, Object requestEntity, Class<T> responseEntityType, HashMap<String, String> queryParams) throws ConfigurationNotFoundException, IOException;

    /**
     * Returns only string /error or success message
     * @param system
     * @param endPoint
     * @param requestEntity
     * @param queryParams
     * @return
     * @throws ConfigurationNotFoundException
     * @throws IOException
     */
    String primitivePost(Boolean exceptionEnable, SYSTEM system, String endPoint, Object requestEntity, HashMap<String, String> queryParams) throws ConfigurationNotFoundException, IOException;

    /**
     * multipart post
     * @param system
     * @param endPoint
     * @param file
     * @param queryParams
     * @return
     * @throws ConfigurationNotFoundException
     * @throws IOException
     */
    String multiPartPost(Boolean exceptionEnable, SYSTEM system, String endPoint, MultipartFile file, HashMap<String, String> queryParams) throws ConfigurationNotFoundException, IOException;

    /**
     * get String by put
     * @param exceptionEnable
     * @param system
     * @param endPoint
     * @param requestEntity
     * @param queryParams
     * @return
     * @throws ConfigurationNotFoundException
     * @throws IOException
     */
    String put(Boolean exceptionEnable, SYSTEM system, String endPoint, Object requestEntity, HashMap<String, String> queryParams) throws ConfigurationNotFoundException, IOException;

}
