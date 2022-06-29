package com.yyd.yyd.frame.httpclient;


import com.yyd.yyd.constants.Const;

import com.yyd.yyd.frame.trace.TraceMdcUtil;
import com.yyd.yyd.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Component
@Slf4j
@SuppressWarnings("unchecked")
public class HttpSimpleClient {

    private RestTemplate restTemplate;

    @Autowired
    public HttpSimpleClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public HttpData postForm(String url, HttpHeaders headers, Map<String, Object> params) {
        log.info("postForm | entry | {} | {}", url, JsonUtils.toJson(params));

        if (headers == null) {
            headers = new HttpHeaders();
        }
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String traceId = TraceMdcUtil.getTraceId();
        if (StringUtils.isNotBlank(traceId)) {
            headers.set(Const.TRACE_ID, traceId);
        }

        MultiValueMap<String, Object> postParams = null;
        if (MapUtils.isNotEmpty(params)) {
            postParams = new LinkedMultiValueMap<>();

            for (String key : params.keySet()) {
                postParams.add(key, params.get(key));
            }
        }

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(postParams, headers);

        try {
            ResponseEntity<String> respEntity = restTemplate.postForEntity(url, entity, String.class);

            return new HttpData(respEntity.getStatusCodeValue(), respEntity.getBody(), respEntity.getHeaders().toSingleValueMap());
        } catch (HttpClientErrorException rex) {
            log.error("postForm | error | {} | {}", url, JsonUtils.toJson(params), rex);

            return new HttpData(rex.getStatusCode().value(), rex.getResponseBodyAsString());
        } catch (Exception ex) {
            log.error("postForm | error | {} | {}", url, JsonUtils.toJson(params), ex);

            throw new RuntimeException(ex);
        }
    }

    public HttpData postJson(String url, HttpHeaders headers, String json) {
        log.info("postJson | entry | {} | {}", url, json);

        if (headers == null) {
            headers = new HttpHeaders();
        }
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
        headers.set("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:16.0) Gecko/20100101 Firefox/16.0");

        String traceId = TraceMdcUtil.getTraceId();
        if (StringUtils.isNotBlank(traceId)) {
            headers.set(Const.TRACE_ID, traceId);
        }

        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        try {
            ResponseEntity<String> respEntity = restTemplate.postForEntity(url, entity, String.class);

            return new HttpData(respEntity.getStatusCodeValue(), respEntity.getBody(), respEntity.getHeaders().toSingleValueMap());
        } catch (HttpClientErrorException rex) {
            log.error("postJson | error | {}", url, rex);

            return new HttpData(rex.getStatusCode().value(), rex.getResponseBodyAsString());
        } catch (Exception ex) {
            log.error("postJson | error | {}", url, ex);

            throw new RuntimeException(ex);
        }
    }

//    public HttpData execGet(String url) {
//        log.info("execGet | entry | {}", url);
//        try {
//            ResponseEntity<String> respEntity = restTemplate.getForEntity(url, String.class);
//            return new HttpData(respEntity.getStatusCodeValue(), respEntity.getBody(), respEntity.getHeaders().toSingleValueMap());
//        } catch (HttpClientErrorException rex) {
//            log.error("execGet | error | {}", url, rex);
//
//            return new HttpData(rex.getStatusCode().value(), rex.getResponseBodyAsString());
//        } catch (Exception ex) {
//            log.error("execGet | error | {}", url, ex);
//
//            throw new RuntimeException(ex);
//        }
//    }

    public HttpData execGet(String url, HttpHeaders headers) {
        log.info("exchangeGet | entry | {}", url);
        if (headers == null) {
            headers = new HttpHeaders();
        }
        String traceId = TraceMdcUtil.getTraceId();
        if (StringUtils.isNotBlank(traceId)) {
            headers.set(Const.TRACE_ID, traceId);
        }

        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<String> respEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

        return new HttpData(respEntity.getStatusCodeValue(), respEntity.getBody(), respEntity.getHeaders().toSingleValueMap());
    }
}
