package com.dheeraj.ekartv3.services.implementations;

import com.dheeraj.ekartv3.helper.Constant;
import com.dheeraj.ekartv3.services.Api;
import com.dheeraj.ekartv3.services.IHttpClient;
import com.dheeraj.ekartv3.services.ITokenGenerationService;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dheeraj Reddy
 */
public class ApiService implements Api {

    private IHttpClient httpClient;
    private ITokenGenerationService tokenGenerationService;

    @Inject
    public ApiService(IHttpClient httpClient, ITokenGenerationService tokenGenerationService) {
        this.httpClient = httpClient;
        this.tokenGenerationService = tokenGenerationService;
    }

    @Override
    public JsonNode get(String url) {
        return httpClient.get(url, generateHeaders());
    }

    @Override
    public JsonNode post(String url, JsonNode body) {
        return httpClient.post(url, body, generateHeaders());
    }

    private Map<String, String> generateHeaders() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put(Constant.AUTHORIZATION_HEADER, tokenGenerationService.generateServerToken());
        return headers;
    }
}
