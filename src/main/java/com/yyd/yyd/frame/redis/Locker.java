package com.yyd.yyd.frame.redis;


import com.yyd.yyd.utils.JsonUtils;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;


@Data
public class Locker {

    private String lockKey;
    private String lockValue;

    public Locker(String lockKey, String lockValue) {
        this.lockKey = lockKey;
        this.lockValue = lockValue;
    }

    public Locker(String lockKey) {
        this.lockKey = lockKey;
    }

    public Locker() {

    }

    public boolean hasLock() {
        return StringUtils.isNotBlank(this.lockValue);
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}
