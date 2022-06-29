package com.yyd.yyd.frame.redis;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
@Slf4j
public class DistributedLockerHelper {

    @Resource
    private DistributedLocker distributedLocker;

    public String getLockKey(String... keys) {
        return String.format("bigink:locker:%s", StringUtils.join(keys, ":"));
    }

    private Locker applyLock(int timeout, String... keys) {
        String lockKey = getLockKey(keys);
        String lockValue = distributedLocker.lock(lockKey, timeout);

        if (StringUtils.isNotBlank(lockValue)) {
            return new Locker(lockKey, lockValue);
        }

        return null;
    }

    public void unlock(Locker locker) {
        if (locker != null) {
            distributedLocker.unlock(locker.getLockKey(), locker.getLockValue());
        }
    }

    public Locker applyBinCardLocker(String userGid) {
        return applyLock(60, "abcl", userGid);
    }

    public Locker appleDelCardLocker(String userGid) {
        return applyLock(60, "adcl", userGid);
    }

    public Locker loanApplyLocker(String userGid) {
        return applyLock(60, "trade", "apply", userGid);
    }

    public Locker manualReviewLocker(String loanGid) {
        return applyLock(60, "trade", "review", loanGid);
    }

    public Locker loanFillDataLocker(String userGid) {
        return applyLock(60, "trade", "filldata", userGid);
    }

    public Locker applyWechatLocker(String lockName) {
        return applyLock(120, lockName);
    }

    public Locker applySendSMSLocker(String userGid, String smsType) {
        return applyLock(60, "assl", userGid, smsType);
    }

}
