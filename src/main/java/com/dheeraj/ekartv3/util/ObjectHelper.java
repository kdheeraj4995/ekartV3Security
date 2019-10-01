package com.dheeraj.ekartv3.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;

/**
 * @author Dheeraj Reddyt
 */
public class ObjectHelper {

    public static String convertObjectToString(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Cannot convert object to string, cause:" + e.getMessage());
        }
    }

    public static Map<String, Object> convertObjectToMap(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(object, Map.class);

    }

    public static String getBase64EncodedString(String s) {
        return getBase64EncodedString(s.getBytes());
    }

    public static String getBase64EncodedString(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String decodeBase64EncodedString(String s) {
        return new String(Base64.getDecoder().decode(s));
    }

    public static <T> T convertStringToObject(String s, Class<T> className) {
        try {
            return new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .readValue(s, className);
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot convert string to object, cause:" + e.getMessage());
        }
    }

}
