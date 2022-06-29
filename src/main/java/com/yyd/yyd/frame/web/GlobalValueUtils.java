package com.yyd.yyd.frame.web;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Slf4j
public class GlobalValueUtils {

    private static final String HEADER_TOKEN = "token";
    private static final String HEADER_UID = "userId";//用户id
    private static final String HEADER_PID = "orgId";//项目id
    private static final String ROLE_NAME = "roleName";

    public static List<String> HEAD_LIST;

    static {
        HEAD_LIST = new ArrayList<>();
        HEAD_LIST.add(HEADER_TOKEN);
        HEAD_LIST.add(HEADER_UID);
        HEAD_LIST.add(HEADER_PID);
    }


    public static String getToken() {
        return getValue(HEADER_TOKEN);
    }

    public static String getUid() {
        String uid = getValue(HEADER_UID);
        return StringUtils.isNotBlank(uid) ? uid : "1";
    }

    public static String getPid() {
        String pid = getValue(HEADER_PID);
        return StringUtils.isNotBlank(pid) ? pid : "1";
    }

    public static String getRoleName() {
        String pid = getValue(ROLE_NAME);
        return StringUtils.isNotBlank(pid) ? pid : "1";
    }

    private static String getValue(String key) {
        return getHeaderValue(key);
    }

    private static String getHeaderValue(String key) {
        try {
            HttpServletRequest request = getRequest();
            return request.getHeader(key);
        } catch (Exception ex) {
            log.error("getHeaderValue | error | {}", key, ex);
        }

        return null;
    }

    private static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    private static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }
}
