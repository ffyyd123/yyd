package com.yyd.yyd.constants.ienum;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

import java.util.Objects;

@Getter
public enum DelStatus {

    active(0),
    inActive(1);

    @EnumValue
    private Integer status;

    DelStatus(Integer status) {
        this.status = status;
    }

    public static DelStatus getDelStatus(Integer status) {
        if (Objects.isNull(status)) {
            return null;
        }
        for (DelStatus delStatus : DelStatus.values()) {
            if (status.equals(delStatus.getStatus())) {
                return delStatus;
            }
        }
        return null;
    }
}
