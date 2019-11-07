package com.theembers.iot.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * json 工具类
 *
 * @author TheEmbers Guo
 * @version 1.0
 * createTime 2018-10-22 11:23
 */
public class JsonUtils {
    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static String str2Json(Object o) throws JsonProcessingException {
        return objectMapper.writeValueAsString(o);
    }

    public static <T> T jsonStr2Obj(String jsonString, Class<T> clazz) throws IOException {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        return objectMapper.readValue(jsonString, clazz);
    }

    public static <T> T jsonStr2Collection(String jsonString, Class<?> collectionClass, Class... elementClasses) throws IOException {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
        return objectMapper.readValue(jsonString, javaType);
    }


}
