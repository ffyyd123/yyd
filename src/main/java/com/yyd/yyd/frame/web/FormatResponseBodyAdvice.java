package com.yyd.yyd.frame.web;


import com.yyd.yyd.constants.icode.RestRespCode;
import com.yyd.yyd.frame.exception.CaliperException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@RestControllerAdvice(basePackages = "com.core.caliper.controller")
@Slf4j
public class FormatResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        // 这里判断是否所有请求都需要包装返回体，本系统默认都需要
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
//        Object res;
//        if (body == null) {
//            res = new RestResponse(RestRespCode.SUCCESS);
//        } else if (body instanceof RestResponse || body instanceof Resource) {
//            res = body;
//        } else {
//            RestResponse restResp = new RestResponse(RestRespCode.SUCCESS);
//            restResp.setData(body);
//
//            res = restResp;
//        }
//
        log.info("ResponseBodyAdvice | url={} | respBody={}", request.getURI().getPath(), body);

        return body;
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public RestResponse exceptionHandler(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        RestRespCode respCode = RestRespCode.PARAMS_ERROR;

//        String message = respCode.getMessage();
//        if (result.hasErrors()) {
//            Set<String> fields = new HashSet<>();
//            result.getFieldErrors().forEach(error -> {
//                fields.add(error.getField());
//            });
//
//            message = message + ": " + StringUtils.join(fields, ",");
//        }

        String message = respCode.getMessage();
        if (result.hasErrors()) {
            Set<String> fields = new HashSet<>();
            result.getFieldErrors().forEach(error -> {
                fields.add(error.getDefaultMessage());
            });

            String msg2 = StringUtils.join(fields, "，");
            if (StringUtils.isNotBlank(msg2)) {
                message = msg2;
            }
        }

        return new RestResponse(respCode.getCode(), message);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    public RestResponse exceptionHandler(ConstraintViolationException exception) {
        RestRespCode respCode = RestRespCode.PARAMS_ERROR;

        String message = respCode.getMessage();
        if (!CollectionUtils.isEmpty(exception.getConstraintViolations())) {
            message = message + ": " + StringUtils.join(
                    exception.getConstraintViolations().stream()
                            .map(ConstraintViolation::getMessageTemplate)
                            .collect(Collectors.toSet()), ",");
        }

        return new RestResponse(respCode.getCode(), message);
    }

    @ExceptionHandler(value = CaliperException.class)
    @ResponseBody
    public RestResponse exceptionHandler(CaliperException mex) {
        log.error("exceptionHandler | CaliperException", mex);
        return new RestResponse(mex);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public RestResponse exceptionHandler(Exception ex) {
        log.error("exceptionHandler | Exception", ex);
        return new RestResponse(RestRespCode.SYS_ERROR);
    }
}
