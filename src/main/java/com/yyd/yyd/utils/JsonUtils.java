package com.yyd.yyd.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;


@Slf4j
public class JsonUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        // 对于空的对象转json的时候不抛出错误
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // 允许属性名称没有引号
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // 允许单引号
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        // 设置输入时忽略在json字符串中存在但在java对象实际没有的属性
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 设置输出时包含属性的风格
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setDefaultPropertyInclusion(
                JsonInclude.Value.construct(JsonInclude.Include.ALWAYS, JsonInclude.Include.NON_NULL));
    }

    /**
     * 序列化，将对象转化为json字符串
     *
     * @param data
     * @return
     */
    public static String toJson(Object data) {
        if (data == null) {
            return null;
        }

        if (data instanceof String) {
            return (String) data;
        }

        try {
            return mapper.writeValueAsString(data);
        } catch (Exception ex) {
            log.error("Convert Object to Json error! | {}", data, ex);

            throw new RuntimeException(ex);
        }
    }

    public static Map<String, Object> toMap(String json) {
        if (StringUtils.isBlank(json)) {
            return null;
        }

        try {
            return mapper.readValue(json, new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception ex) {
            log.error("parse json to map error! | {}", json, ex);

            throw new RuntimeException(ex);
        }
    }

    /**
     * 反序列化，将json字符串转化为对象
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        if (StringUtils.isBlank(json)) {
            return null;
        }

        try {
            return mapper.readValue(json, clazz);
        } catch (Exception ex) {
            log.error("parse json to class error! | {} | {}", json, clazz.getSimpleName(), ex);

            throw new RuntimeException(ex);
        }
    }
}
