package com.dheeraj.ekartv3.services.implementations;

import com.dheeraj.ekartv3.services.IHttpClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import play.libs.Json;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;
import play.libs.ws.WSResponse;

import java.util.Map;

/**
 * @author Dheeraj Reddy
 */
public class HttpClient implements IHttpClient {
    private WSClient wsClient;

    @Inject
    public HttpClient(WSClient wsClient) {
        this.wsClient = wsClient;
    }

    @Override
    public JsonNode get(String url) {
        WSResponse wsResponse = getWsRequest(url, null).get().toCompletableFuture().join();
        return generateJsonResponse(wsResponse);
    }

    @Override
    public JsonNode get(String url, Map<String, String> headers) {
        WSResponse wsResponse = getWsRequest(url, headers).get().toCompletableFuture().join();
        return generateJsonResponse(wsResponse);
    }

    @Override
    public JsonNode post(String url, JsonNode body, Map<String, String> headers) {
        WSResponse wsResponse = getWsRequest(url, headers).post(body).toCompletableFuture().join();
        return generateJsonResponse(wsResponse);
    }

    @Override
    public JsonNode put(String url, JsonNode body, Map<String, String> headers) {
        WSResponse wsResponse = getWsRequest(url, headers).put(body).toCompletableFuture().join();
        return generateJsonResponse(wsResponse);
    }

    private WSRequest getWsRequest(String url, Map<String, String> headers) {
        WSRequest wsRequest = wsClient.url(url);
        wsRequest = updateHeaders(wsRequest, headers);
        return wsRequest;
    }

    private WSRequest updateHeaders(WSRequest wsRequest, Map<String, String> headers) {
        if (headers != null) {
            headers.entrySet().forEach(header -> {
                wsRequest.addHeader(header.getKey(), header.getValue());
            });
        }
        return wsRequest;
    }

    protected JsonNode generateJsonResponse(WSResponse response) {
        if (response.getBody().equalsIgnoreCase("")) {
            return Json.parse("{}");
        } else {
            return Json.parse(response.getBody());
        }
    }
}
