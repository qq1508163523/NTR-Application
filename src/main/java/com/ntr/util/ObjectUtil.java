package com.ntr.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;


public class ObjectUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows({JsonProcessingException.class})
    public static String objectToJsonString(Object object) {
        return objectMapper.writeValueAsString(object);
    }

    @SneakyThrows({JsonProcessingException.class})
    public static <T> T jsonStringToObject(String jsonStr,Class<T> clazz) {
        return objectMapper.readValue(jsonStr,clazz);
    }
}
