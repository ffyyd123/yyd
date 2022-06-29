package com.yyd.yyd.frame.httpclient;


import com.yyd.yyd.utils.JsonUtils;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;


@Data
public class HttpData {
    private int statusCode;
    private String body;
    private Map<String, String> respHeaders;

    public HttpData(int statusCode, String body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public HttpData(int statusCode, String body, Map<String, String> respHeaders) {
        this.statusCode = statusCode;
        this.body = body;
        this.respHeaders = respHeaders;
    }

    public HttpData(String body) {
        this.statusCode = 200;
        this.body = body;
    }

    public HttpData() {
        this.statusCode = 250;
    }

    public boolean isOK() {
        return statusCode == 200 && StringUtils.isNotBlank(body);
    }

    public Map<String, Object> bodyMap() {
        return JsonUtils.toMap(body);
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}
