package com.yyd.yyd.frame.web;


import com.yyd.yyd.constants.icode.IRespCode;
import com.yyd.yyd.constants.icode.RestRespCode;
import com.yyd.yyd.frame.exception.CaliperException;
import com.yyd.yyd.utils.JsonUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;


@ApiModel(description = "响应模板")
@Data
@Builder
@AllArgsConstructor
public class RestResponse<T> implements Serializable {

    @ApiModelProperty(value = "响应编码", example = "0000", required = true, position = 0)
    private String code;
    @ApiModelProperty(value = "响应描述", example = "success", required = true, position = 1)
    private String message;
    @ApiModelProperty(value = "数据明细", position = 2)
    private T data;

    public RestResponse(T data) {
        this.data = data;
        reset(RestRespCode.SUCCESS);
    }

    public RestResponse() {
        reset(RestRespCode.SUCCESS);
    }

    public RestResponse(IRespCode respCode) {
        setValue(respCode.getCode(), respCode.getMessage());
    }

    public RestResponse(CaliperException rex) {
        setValue(rex.getCode(), rex.getMessage());
    }

    public RestResponse(String code, String message) {
        setValue(code, message);
    }

    public void reset(IRespCode respCode) {
        setValue(respCode.getCode(), respCode.getMessage());
    }

    public void reset(CaliperException rex) {
        setValue(rex.getCode(), rex.getMessage());
    }

    private void setValue(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}
