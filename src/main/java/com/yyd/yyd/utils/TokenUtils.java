package com.yyd.yyd.utils;

import com.yyd.yyd.frame.web.GlobalValueUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TokenUtils {

//    @Value("${login.token.encode.key:F78eb561f574f347}")
    private String encodeToken;

    /**
     * 加密token
     * @param id 用户表主键
     * @param userGid 用户ID
     * @param username 用户名
     * @param token 登录redis存储的token
     * @param effectiveTime 配置有效时间
     * @return 返回加密后的token
     */
    public String encrypt(Long id, String userGid, String username, String token, Long effectiveTime){
        effectiveTime = currentTime() + effectiveTime;
        String encryption = id + encodeToken+ effectiveTime;
        String md5Str = com.yyd.yyd.utils.MD5Utils.md5Code(encryption);
        log.info("encrypt token mdt5Str:{}", md5Str);
        String splitStr = id + "|" + userGid + "|" + username + "|" + token + "|" + md5Str + "|" + effectiveTime;
        String encryptToken = com.yyd.yyd.utils.Base64Utils.encodeStr(splitStr);
        log.info("encrypt token encryptToken:{}", encryptToken);
        return encryptToken;
    }

    /**
     * 解密token
     * @param token 请求头携带的token
     * @return 返回解密后的token集合
     */
    public List<String> decrypt(String token) {
        token = com.yyd.yyd.utils.Base64Utils.decodeStr(token);
        String[] tokens = token.split("\\|");
        String encryption = tokens[0] + encodeToken + tokens[tokens.length - 1];
        String md5Str = com.yyd.yyd.utils.MD5Utils.md5Code(encryption);
        log.info("decrypt token md5Str:{},tokens encrypt:{}", md5Str,tokens[3]);
        if (!md5Str.equals(tokens[4])) {
            return Arrays.asList();
        }
        return Arrays.stream(tokens).collect(Collectors.toList());
    }

    /**
     * 获取当前时间，单位为秒
     * @return 返回long类型的时间戳
     */
    private long currentTime() {
        return (System.currentTimeMillis() / 1000);
    }

    public String getCurrentUserGid() {
        String token = GlobalValueUtils.getToken();
        if (com.yyd.yyd.utils.StringUtils.isBlank(token)) {
            return null;
        }
        List<String> tokenList = decrypt(token);
        if (CollectionUtils.isEmpty(tokenList)) {
            return null;
        }

        return tokenList.get(1);
    }
}
