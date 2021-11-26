package com.sysq.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {


    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper = new ObjectMapper();
        // 设置null值不参与序列化(字段不被显示)
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 该特性决定了当遇到未知属性（没有映射到属性，没有任何setter或者任何可以处理它的handler），是否应该抛出一个JsonMappingException异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 允许特殊转义字符
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        // 该特性决定是否接受单一数组（JSON）值到Java集合类型。如果允许，集合反序列化将尝试处理。
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    }

    public static String serialize(Object object) {
        if (object == null) {
            return "";
        } else {
            try {
                return objectMapper.writeValueAsString(object);
            } catch (JsonProcessingException var2) {
                return "";
            }
        }
    }
    public static <T> T deserialize(String jsonString, TypeReference<T> typeReference) {
        if (jsonString != null && typeReference != null) {
            try {
                return objectMapper.readValue(jsonString, typeReference);
            } catch (JsonProcessingException var3) {
                return null;
            }

        }
        return null;
    }

}
