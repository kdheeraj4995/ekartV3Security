package com.dheeraj.ekartv3.services;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;

/**
 * @author Dheeraj Reddy
 */
public interface IHttpClient {

    JsonNode get(String url, Map<String, String> headers);

    JsonNode post(String url, JsonNode body, Map<String, String> headers);

    JsonNode put(String url, JsonNode body, Map<String, String> headers);

}
