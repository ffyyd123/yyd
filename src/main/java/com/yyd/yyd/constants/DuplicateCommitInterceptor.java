package com.yyd.yyd.constants;


import com.yyd.yyd.constants.icode.RestRespCode;
import com.yyd.yyd.frame.redis.RedisHelper;
import com.yyd.yyd.frame.web.RestResponse;
import com.yyd.yyd.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.HandlerMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@Component
public class DuplicateCommitInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisHelper helper;

    /**
     * 进入controller层之前拦截请求
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof ResourceHttpRequestHandler) {
            // 静态资源不做权限处理
            return true;
        }
        // 获取 token
//        String token = request.getHeader("token");
//        if (token == null) {
//            token = request.getParameter("token");
//        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        //方法上的DuplicateCommitLimit注解
        DuplicateCommitLimit duplicateCommitLimit = handlerMethod.getMethodAnnotation(DuplicateCommitLimit.class);
        if (duplicateCommitLimit != null) {
            String requestURI = request.getRequestURI();
            log.info("preHandler requestURI:{}", requestURI);
            boolean isLimited = helper.getValue("duplicate_commit" + requestURI) != null;
            log.info("preHandler isLimited :{}", isLimited);
            if (isLimited) {
                sendDuplicateCommitMsg(response);//返回被拦截提示信息
                return false;
            } else {
                helper.setValue("duplicate_commit" + requestURI, "duplicate_commit", duplicateCommitLimit.time());
            }
         }

        return true;
    }

    private void sendDuplicateCommitMsg(HttpServletResponse response) throws IOException {
        RestResponse restResponse = new RestResponse();
        restResponse.reset(RestRespCode.SYS_ERROR_FREQUENTLY_REQ);
        PrintWriter writer = response.getWriter();
        String json = JsonUtils.toJson(restResponse);
        writer.append(json);
    }
}
