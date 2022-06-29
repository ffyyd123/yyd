package com.yyd.yyd.frame.trace;

import cn.hutool.core.util.IdUtil;

import com.yyd.yyd.constants.Const;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

/**
 * Created by ye.r.x on 2019/12/31.
 */
@Slf4j
public class TraceMdcUtil {

    public static String getTraceId() {
        try {
            String traceId = MDC.get(Const.TRACE_ID);
            if (StringUtils.isBlank(traceId)) {
                traceId = createTraceId();
                MDC.put(Const.TRACE_ID, traceId);
            }

            return traceId;
        } catch (IllegalArgumentException ex) {
            log.warn("getTraceId | 获取traceId失败", ex);
        }

        return null;
    }


    public static void setTraceIdIfAbsent() {
        try {
            if (StringUtils.isBlank(MDC.get(Const.TRACE_ID))) {
                MDC.put(Const.TRACE_ID, createTraceId());
            }
        } catch (IllegalArgumentException ex) {
            log.warn("setTraceIdIfAbsent | traceId设置失败", ex);
//            MDC.clear();
        }
    }

    public static void setTraceIdIfAbsent(String traceId) {
        try {
            if (StringUtils.isBlank(MDC.get(Const.TRACE_ID))) {
                if (StringUtils.isNotBlank(traceId)) {
                    MDC.put(Const.TRACE_ID, traceId);
                } else {
                    MDC.put(Const.TRACE_ID, createTraceId());
                }
            }
        } catch (IllegalArgumentException ex) {
            log.warn("setTraceIdIfAbsent | traceId设置失败", ex);
//            MDC.clear();
        }
    }

    public static void setTraceId(String traceId) {
        try {
            if (StringUtils.isNotBlank(traceId)) {
                MDC.put(Const.TRACE_ID, traceId);
            } else {
                MDC.put(Const.TRACE_ID, createTraceId());
            }
        } catch (IllegalArgumentException ex) {
            log.warn("setTraceIdIfAbsent | traceId设置失败", ex);
//            MDC.clear();
        }
    }

    private static String createTraceId() {
        return IdUtil.objectId();
    }
}
