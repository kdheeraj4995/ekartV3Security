package com.dheeraj.ekartv3.services.implementations;

import com.dheeraj.ekartv3.services.Api;
import com.dheeraj.ekartv3.services.IHttpClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dheeraj Reddy
 */
public class ApiService implements Api {

    private IHttpClient httpClient;

    @Inject
    public ApiService(IHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public JsonNode get(String url, String context) {
        return httpClient.get(url, generateServerToken(context));
    }

    @Override
    public JsonNode post(String url, JsonNode body, String context) {
        return httpClient.post(url, body, generateServerToken(context));
    }

    private Map<String, String> generateServerToken(String context) {
        return new HashMap<>();
    }
}
