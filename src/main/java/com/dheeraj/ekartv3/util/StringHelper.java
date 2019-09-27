package com.dheeraj.ekartv3.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import play.libs.Json;

import java.io.IOException;
import java.util.Base64;
import java.util.Collection;

/**
 * @author Dheeraj Reddyt
 */
public class StringHelper {

    public static String convertObjectToString(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Cannot convert object to string");
        }
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
            return new ObjectMapper().readValue(s, className);
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot convert string to object");
        }
    }

}
