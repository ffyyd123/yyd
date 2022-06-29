package com.yyd.yyd.frame.trace;


import com.yyd.yyd.constants.Const;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ye.r.x on 2019/12/31.
 */
@Slf4j
public class TraceInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            MDC.remove(Const.TRACE_ID);
            String traceId = request.getHeader(Const.TRACE_ID);
            TraceMdcUtil.setTraceId(traceId);
        } catch (Exception ex) {
            log.error("preHandle | traceId设置失败！", ex);
        }
        return true;
    }
}
