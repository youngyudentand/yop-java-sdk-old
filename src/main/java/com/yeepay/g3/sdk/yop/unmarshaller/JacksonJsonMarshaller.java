package com.yeepay.g3.sdk.yop.unmarshaller;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.yeepay.g3.sdk.yop.exception.YopClientException;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * <pre>
 *    将响应对象流化成JSON。 {@link ObjectMapper}是线程安全的。
 * </pre>
 *
 * @author wang.bao
 * @version 1.0
 */
public class JacksonJsonMarshaller {

    private JacksonJsonMarshaller() {

    }

    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        objectMapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
        objectMapper.setSerializationInclusion(Include.NON_EMPTY);
        objectMapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector());
    }

    public static <T> T unmarshal(String content, Class<T> objectType) {
        try {
            return objectMapper.readValue(content, objectType);
        } catch (IOException e) {
            throw new YopClientException(e.getMessage(), e);
        }
    }

    public static String marshal(Object content) {
        try {
            return objectMapper.writeValueAsString(content);
        } catch (JsonProcessingException e) {
            throw new YopClientException(e.getMessage(), e);
        }
    }

}
