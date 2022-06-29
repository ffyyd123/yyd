package com.yyd.yyd.frame.httpclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {

        RestTemplate restTemplate = new RestTemplate(factory);

        List<HttpMessageConverter<?>> partConverters = new ArrayList<HttpMessageConverter<?>>();

        partConverters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
        partConverters.add(new ResourceHttpMessageConverter());
        partConverters.add(new ByteArrayHttpMessageConverter());

        FormHttpMessageConverter fc = new FormHttpMessageConverter();
        fc.setPartConverters(partConverters);
        restTemplate.getMessageConverters().addAll(Arrays.asList(fc, new MappingJackson2HttpMessageConverter()));

        return restTemplate;
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        //ms
        factory.setReadTimeout(60000);
        //ms
        factory.setConnectTimeout(20000);
        return factory;
    }
}
