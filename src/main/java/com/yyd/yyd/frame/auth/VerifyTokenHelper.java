package com.yyd.yyd.frame.auth;

import com.yyd.yyd.constants.icode.RestRespCode;
import com.yyd.yyd.frame.exception.CaliperException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class VerifyTokenHelper {

//    @Value("${bridge.api.ak}")
    private String aK;
//    @Value("${bridge.api.sk}")
    private String sK;

    public void verifyToken(String token) throws CaliperException {
        AuthTokenHelper tokenHelper = new AuthTokenHelper(aK, sK);
        if (!tokenHelper.verifyToken(token)) {
            throw new CaliperException(RestRespCode.AUTH_ERROR);
        }
    }
}
