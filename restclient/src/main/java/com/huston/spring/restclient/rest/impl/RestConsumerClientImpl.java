package com.huston.spring.restclient.rest.impl;

import com.huston.spring.restclient.SYSTEM;
import com.huston.spring.restclient.authentication.*;
import com.huston.spring.restclient.authentication.model.RestApiAuthenticationHeader;
import com.huston.spring.restclient.config.Exception.ConfigurationNotFoundException;
import com.huston.spring.restclient.config.model.Configuration;
import com.huston.spring.restclient.config.service.ConfigurationDBService;
import com.huston.spring.restclient.rest.*;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


/**
 * Request factory class for get methods
 * Authentication providers
 */
public class RestConsumerClientImpl extends RestTemplate implements RestConsumerClient, ApiAuthenticationAware {

    private final Logger LOGGER = LoggerFactory.getLogger(RestConsumerClientImpl.class);

    private final String EMAILQUERYPARAMKEY = "auth_email";
    private ConfigurationDBService configurationDBService;
    private ApiAuthenticationManagerBuilder authenticationManagerBuilder;

    public RestConsumerClientImpl(ConfigurationDBService configurationDBService,
                                  ApiAuthenticationProviderManagerBuilder authenticationManagerBuilder
    ) {

        LOGGER.info("Initializing RestConsumerClient bean");

        List<ClientHttpRequestInterceptor> interceptors
                = super.getInterceptors();
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }
        interceptors.add(new RestApiAuthenticationInterceptor());

        super.setInterceptors(interceptors);
        super.setRequestFactory(new HttpComponentsClientHttpRequestWithBodyFactory());
        super.setErrorHandler(new RestConsumerErrorHandler());

        this.configurationDBService = configurationDBService;
        this.authenticationManagerBuilder = authenticationManagerBuilder;

        LOGGER.info("Initializing finished of RestConsumerClient bean");
    }

    private static <T> T nonNull(@org.springframework.lang.Nullable T result) {
        Assert.state(result != null, "No result");
        return result;
    }

    @Override
    public <B extends SystemAwareCommunicationEntity, R extends SystemAwareCommunicationEntity> R authPost(Boolean exceptionEnable, SYSTEM system, String endPoint, B body, Class<R> responseEntityType) throws ConfigurationNotFoundException, IOException{
        LOGGER.info("Executing authPost");
        LOGGER.debug("Request body Object ", body);
        ResponseEntity<R> responseEntity = customExchange(exceptionEnable, expandUri(system, endPoint), system, HttpMethod.POST, body, ParameterizedTypeReference.forType(responseEntityType));
        LOGGER.info("Finished authPost");
        LOGGER.debug("Result of authentication", responseEntity);
        return mapSystemProperties(system,responseEntity.getBody());
    }

    @Override
    public <T extends SystemAwareCommunicationEntity> T getObject(Boolean exceptionEnable, SYSTEM system, String endPoint, Class<T> responseEntityType, @Nullable HashMap<String, String> queryParams, String... pathParameters) throws ConfigurationNotFoundException, IOException {
        LOGGER.info("Executing getObject");
        LOGGER.debug("Request body Object ", queryParams);
        ResponseEntity<T> responseEntity = customExchange(exceptionEnable, expandUri(system, endPoint, queryParams), system, HttpMethod.POST, null, ParameterizedTypeReference.forType(responseEntityType), pathParameters);
        LOGGER.info("Finished getObject");
        LOGGER.debug("Result of getObject ", responseEntity);
        return mapSystemProperties(system,responseEntity.getBody());
    }

    @Override
    public <T extends SystemAwareCommunicationEntity> List<T> getList(Boolean exceptionEnable, SYSTEM system, String endPoint, ParameterizedTypeReference parameterizedTypeReference, @Nullable HashMap<String, String> queryParams) throws ConfigurationNotFoundException, IOException {
        LOGGER.info("Executing getList");
        LOGGER.debug("Request body Object ", queryParams);
        ResponseEntity<List<T>> responseEntity = customExchange(exceptionEnable, expandUri(system, endPoint, queryParams), system, HttpMethod.GET, null, parameterizedTypeReference);
        LOGGER.info("Finished getList");
        LOGGER.debug("Result of getList ", responseEntity);
        return mapSystemProperties(system,responseEntity.getBody());
    }

    @Override
    public <T extends SystemAwareCommunicationEntity> HashMap<String, T> getMap(Boolean exceptionEnable, SYSTEM system, String endPoint, ParameterizedTypeReference parameterizedTypeReference, @Nullable HashMap<String, String> queryParams) throws ConfigurationNotFoundException, IOException {
        LOGGER.info("Executing getMap");
        LOGGER.debug("Request body Object ", queryParams);
        ResponseEntity<HashMap<String, T>> responseEntity = customExchange(exceptionEnable, expandUri(system, endPoint, queryParams), system, HttpMethod.GET, null, parameterizedTypeReference);
        LOGGER.info("Finished getMap");
        LOGGER.debug("Result of getMap ", responseEntity);
        return mapSystemProperties(system,responseEntity.getBody());
    }

    @Override
    public <T> T post(Boolean exceptionEnable, SYSTEM system, String endPoint, Object requestEntity, Class<T> responseEntityType, HashMap<String, String> queryParams) throws ConfigurationNotFoundException, IOException {
        LOGGER.info("Executing post");
        LOGGER.debug("Request body Object ", queryParams, requestEntity);
        ResponseEntity<T> responseEntity = customExchange(exceptionEnable, expandUri(system, endPoint, queryParams), system, HttpMethod.POST, new HttpEntity(requestEntity), ParameterizedTypeReference.forType(responseEntityType));
        LOGGER.info("Finished post");
        LOGGER.debug("Result of post ", responseEntity);
        return responseEntity.getBody();
    }

    @Override
    public String primitivePost(Boolean exceptionEnable, SYSTEM system, String endPoint, Object requestEntity, HashMap<String, String> queryParams) throws ConfigurationNotFoundException, IOException {
        LOGGER.info("Executing primitivePost");
        LOGGER.debug("Request body Object ", queryParams, requestEntity);
        ResponseEntity<String> responseEntity = customExchange(exceptionEnable, expandUri(system, endPoint, queryParams), system, HttpMethod.POST, new HttpEntity(requestEntity), ParameterizedTypeReference.forType(String.class));
        LOGGER.info("Finished primitivePost");
        LOGGER.debug("Result of primitivePost ", responseEntity);
        return responseEntity.getBody();
    }

    @Override
    public String put(Boolean exceptionEnable, SYSTEM system, String endPoint, Object requestEntity, HashMap<String, String> queryParams) throws ConfigurationNotFoundException, IOException {
        LOGGER.info("Executing put");
        LOGGER.debug("Request body Object ", queryParams, requestEntity);
        ResponseEntity<String> responseEntity = customExchange(exceptionEnable, expandUri(system, endPoint, queryParams), system, HttpMethod.PUT, new HttpEntity(requestEntity), ParameterizedTypeReference.forType(String.class));
        LOGGER.info("Finished put");
        LOGGER.debug("Result of put ", responseEntity);
        return responseEntity.getBody();
    }

    @Override
    public String multiPartPost(Boolean exceptionEnable, SYSTEM system, String endPoint, MultipartFile file, HashMap<String, String> queryParams) throws ConfigurationNotFoundException, IOException{
        LOGGER.info("Executing put");
        LOGGER.debug("Request body Object ", queryParams, file.getName());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new FileSystemResource(convertMultipartToFile(file)));
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> responseEntity = customExchange(exceptionEnable, expandUri(system, endPoint, queryParams), system, HttpMethod.POST, requestEntity, ParameterizedTypeReference.forType(String.class));
        return responseEntity.getBody();
    }

    @Override
    public <B, R> R getObject(Boolean exceptionEnable, SYSTEM system, String endPoint, B requestEntity, Class<R> responseEntityType, HashMap<String, String> queryParams, String... pathParameters) throws ConfigurationNotFoundException, IOException {

        ResponseEntity<R> responseEntity = customExchange(exceptionEnable, expandUri(system, endPoint, queryParams), system, HttpMethod.GET, requestEntity, ParameterizedTypeReference.forType(responseEntityType), pathParameters);
        return responseEntity.getBody();
    }

    /**
     * Decide whether exceptions from external application must be hidden.
     * If the {@param exceptionEnable} is true exception will be thrown and its false exception will be ignored
     * @param exceptionEnable
     * @param url
     * @param method
     * @param body
     * @param responseType
     * @param pathParameters
     * @param <B>
     * @param <R>
     * @return
     * @throws IOException
     */
    public <B,R> ResponseEntity<R> customExchange(Boolean exceptionEnable, String url, SYSTEM system, HttpMethod method,@Nullable B body,
                                                @Nullable ParameterizedTypeReference<R> responseType, String... pathParameters) throws IOException {
        ResponseEntity responseEntity = null;

        if(exceptionEnable){
            responseEntity = callEndpoint(url, system, method, body, responseType, pathParameters);
        }
        else {
            try{
                return callEndpoint(url, system, method, body, responseType, pathParameters);
            }catch (Exception e){
                responseEntity = ResponseEntity.ok(null);
            }
        }
        return responseEntity;
    }

    /**
     * Setting up {@link HttpEntity} with body and header
     * Authentication for apis
     * @param url
     * @param method
     * @param body
     * @param responseType
     * @param pathParameters
     * @param <B>
     * @param <R>
     * @return
     * @throws IOException
     */
    private <B, R> ResponseEntity<R> callEndpoint(String url, SYSTEM system, HttpMethod method, @Nullable B body,
                              @Nullable ParameterizedTypeReference<R> responseType, String... pathParameters) throws IOException{
        Type responseTypeType = responseType.getType();


        /**
         * TODO use factory or reusable method to set the system aware variables in header relevant to the Api authentication Provider
         *
         * If the Authentication provider has been implemented base on system specific properties, following setting up must be implemented accordingly (setting up system properties here
         * to use them in provider implementation
         *
         */
        SystemAwareApiHttpHeader restApiHeader = new SystemAwareApiHttpHeader();
        restApiHeader.setSystem(system);


        setAuthentication(restApiHeader);

        HttpEntity httpEntity = new HttpEntity(body, restApiHeader.getHttpHeaders());
        RequestCallback requestCallback = httpEntityCallback(httpEntity, responseTypeType);
        ResponseExtractor<ResponseEntity<R>> responseExtractor = responseEntityExtractor(responseTypeType);
        return nonNull(customExecute(url, method, requestCallback, responseExtractor, pathParameters));
    }

    @Nullable
    public <T> T customExecute(String url, HttpMethod method, @Nullable RequestCallback requestCallback,
                               @Nullable ResponseExtractor<T> responseExtractor, Object... uriVariables) throws IOException {

        for (Object uriVariable: uriVariables) {

            url = url + "/" + uriVariable;
        }

        /**
         * TODO following uri handler dons't inject uri variables in to url (need more diggings to find out the issue might be a config issue)
         */
        URI expanded = getUriTemplateHandler().expand(url, Arrays.asList(uriVariables));

        return customDoExecute(expanded, method, requestCallback, responseExtractor);
    }

    @Nullable
    protected <T> T customDoExecute(URI url, @Nullable HttpMethod method, @Nullable RequestCallback requestCallback,
                                    @Nullable ResponseExtractor<T> responseExtractor) throws IOException {

        Assert.notNull(url, "URI is required");
        Assert.notNull(method, "HttpMethod is required");
        ClientHttpResponse response = null;
        ClientHttpRequest request = createRequest(url, method);

        if (requestCallback != null) {
            requestCallback.doWithRequest(request);
        }

        response = request.execute();
        handleResponse(url, method, response);
        T result = responseExtractor.extractData(response);
        if (response != null) {
            response.close();
        }
        return (responseExtractor != null ? result : null);
    }

    /**
     * This method get executed during the chain of request
     * @param headers
     */
    @Override
    public final void setAuthentication(RestApiAuthenticationHeader headers) {
        getAuthenticationManager().setAuthentication(headers);
    }

    /**
     * Overrider this method to change the authentication manager
     * @return
     */
    @Override
    public ApiAuthenticationManager getAuthenticationManager() {
        return new ApiAuthenticationManagerDelegator(authenticationManagerBuilder);
    }

    private String expandUri(SYSTEM system, String endPoint, @Nullable HashMap<String, String> queryParams) throws ConfigurationNotFoundException {

        String uri = getSystemUrl(system) + endPoint;
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(uri);
        if (queryParams != null) {
            queryParams.forEach(
                    (s, s2) -> {
                        uriBuilder.queryParam(s, s2);
                    }
            );
        }
        String finalUri = uriBuilder.toUriString();

        return finalUri;
    }

    /**
     * Private apis
     */
    private String expandUri(SYSTEM system, String endPoint) throws ConfigurationNotFoundException {
        String uri = getSystemUrl(system) + endPoint;
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(uri);
        String finalUri = uriBuilder.toUriString();
        return finalUri;
    }

    private String getSystemUrl(SYSTEM system) throws ConfigurationNotFoundException {
        Configuration configuration = configurationDBService.getConfiguration(system.getConfigCode());
        if (configuration == null) {
            throw new ConfigurationNotFoundException("Cannot find the configuration");
        }
        return configuration.getValue();
    }

    private static File convertMultipartToFile(MultipartFile file) throws IOException
    {
        File converDatedFile = new File(file.getOriginalFilename());
        converDatedFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(converDatedFile);
        fos.write(file.getBytes());
        fos.close();
        return converDatedFile;
    }

    private static <T extends SystemAwareCommunicationEntity> List<T> mapSystemProperties(SYSTEM system, List<T> list){
        if(list !=null){
            list.stream().forEach(element -> element.setSystem(system));
            return list;
        }
        return null;

    }

    private static <T extends SystemAwareCommunicationEntity> HashMap<String, T> mapSystemProperties(SYSTEM system, HashMap<String, T> map){
        if(map !=null) {
            map.entrySet().stream().forEach(element -> element.getValue().setSystem(system));
            return map;
        }
        return null;
    }

    private static <T extends SystemAwareCommunicationEntity> T mapSystemProperties(SYSTEM system, T object){
        if(object != null){
            object.setSystem(system);
            return object;
        }
        return null;
    }

    private static final class HttpComponentsClientHttpRequestWithBodyFactory extends HttpComponentsClientHttpRequestFactory {
        @Override
        protected HttpUriRequest createHttpUriRequest(HttpMethod httpMethod, URI uri) {
            if (httpMethod == HttpMethod.GET) {
                return new HttpGetRequestWithEntity(uri);
            }
            return super.createHttpUriRequest(httpMethod, uri);
        }
    }

    private static final class HttpGetRequestWithEntity extends HttpEntityEnclosingRequestBase {
        public HttpGetRequestWithEntity(final URI uri) {
            super.setURI(uri);
        }

        @Override
        public String getMethod() {
            return HttpMethod.GET.name();
        }
    }
}
