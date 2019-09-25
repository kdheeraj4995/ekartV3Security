package com.dheeraj.ekartv3.services;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author Dheeraj Reddy
 */
public interface Api {

    JsonNode get(String url, String context);

    JsonNode post(String url, JsonNode body, String context);

}
