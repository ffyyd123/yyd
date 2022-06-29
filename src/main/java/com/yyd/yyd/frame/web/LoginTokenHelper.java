package com.yyd.yyd.frame.web;


import com.yyd.yyd.frame.redis.RedisHelper;
import com.yyd.yyd.utils.TokenUtils;
import com.yyd.yyd.utils.Utility;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


@Component
@Slf4j
public class LoginTokenHelper {

//    @Value("${login.token.valid.switch:true}")
    private boolean validSwitch;
//    @Value("${login.token.ttl.pc:60000}")
    private Long pcTTL;

    private static final String TERMINAL_PC = "pc";

    private static final String TOKEN_PREFIX = "Basic ";

    @Resource
    private RedisHelper redisHelper;

    @Autowired
    private TokenUtils tokenUtils;


    public String resetLoginToken(Long id,String userGid, String oauthToken,String username) {
        String token = oauthToken;
        if (StringUtils.isBlank(oauthToken)) {
            token = Utility.simpleUUID();
        }
        redisHelper.setValue(getTokenKey(TERMINAL_PC, userGid), token, pcTTL);
        token = tokenUtils.encrypt(id, userGid, username, token, pcTTL);
        return token;
    }

    public boolean validToken() {
        try {
            if (!validSwitch) {
                return true;
            }
            String roleName = GlobalValueUtils.getRoleName();
            log.error("getToken roleName:{}", roleName);
            String token = GlobalValueUtils.getToken();
            String userGid = GlobalValueUtils.getUid();
            if (StringUtils.isBlank(token) ){//|| StringUtils.isBlank(userGid)) {
                return false;
            }
            List<String> tokens = tokenUtils.decrypt(token);
            if (CollectionUtils.isNotEmpty(tokens)) {
                userGid = tokens.get(1);
                token = tokens.get(3);
            }
            String key = getTokenKey(TERMINAL_PC, userGid);

            String value = redisHelper.getValue(key);
            if (token.equals(value)) {
                return true;
            }
        } catch (Exception ex) {
            log.error("validToken | error", ex);
        }

        return false;
    }

    private String getTokenKey(String terminal, String userGid) {
        return String.format("chalk:lt:%s:%s", userGid, terminal);
    }
}
