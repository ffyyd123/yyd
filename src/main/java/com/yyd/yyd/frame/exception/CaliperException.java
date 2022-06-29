package com.yyd.yyd.frame.exception;


import com.yyd.yyd.constants.icode.IRespCode;
import com.yyd.yyd.utils.JsonUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class CaliperException extends RuntimeException {

    private String code;
    private String message;

    public CaliperException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public CaliperException(IRespCode respCode) {
        this.code = respCode.getCode();
        this.message = respCode.getMessage();
    }

    public CaliperException(IRespCode respCode, Object... formatMessage) {
        super();
        this.code = respCode.getCode();
        this.message = respCode.getFormattedMessage(formatMessage);
    }

    public CaliperException(IRespCode respCode, Throwable cause) {
        super(cause);
        this.code = respCode.getCode();
        this.message = respCode.getMessage();
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}
