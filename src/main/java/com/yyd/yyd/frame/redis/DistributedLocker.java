package com.yyd.yyd.frame.redis;


import com.yyd.yyd.utils.Utility;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
@Slf4j
public class DistributedLocker {

    private final static String LOCK_PREFIX = "bigink:lock:";
    private final static String VIP_LOCK_PREFIX = "vip-";
    private final int MAX_LOCKER_TIME = 3600 * 6;   // seconds-6h

    @Resource
    private RedisHelper redisHelper;

    /**
     * 获取普通锁
     *
     * @param name    锁名称（后缀）
     * @param timeout 锁定时间，单位-秒
     * @return 钥匙
     */
    protected String lock(String name, int timeout) {
        if (timeout < 1 || timeout > MAX_LOCKER_TIME) {
            log.error("invalid timeout. It must be between 1 and 3600");
            return null;
        }

        if (StringUtils.isBlank(name)) {
            log.error("invalid name. It's blank");
            return null;
        }

        try {
            String key = lockerKey(name);
            if (null != redisHelper.getValue(key)) {
                log.info("Failed to acquire the locker, because it's locked by other. | {}", name);

                if (redisHelper.getTTL(key) <= 0) {
                    log.warn("reset the timeout of bad distribute locker: {}", key);
                    redisHelper.setTTL(key, timeout);
                }

                return null;
            }

            String lockId = Utility.simpleUUID();
            if (redisHelper.setNX(key, lockId, timeout)) {
                return lockId;
            }
        } catch (Exception ex) {
            log.error("lock | Failed to lock | {} | {}", name, timeout, ex);
        }

        return null;
    }

    // 永久锁
    protected String foreverLock(String name) {
        if (StringUtils.isBlank(name)) {
            log.error("invalid name. It's blank");
            return null;
        }

        try {
            String key = lockerKey(name);
            if (null != redisHelper.getValue(key)) {
                return null;
            }

            String lockId = Utility.simpleUUID();
            if (redisHelper.setNX(key, lockId)) {
                return lockId;
            }
        } catch (Exception ex) {
            log.error("foreverLock | Failed to lock | {}", name, ex);
        }

        return null;
    }

    /**
     * 释放锁
     *
     * @param name
     * @param lockId
     * @return
     */
    protected boolean unlock(String name, String lockId) {
        log.info("unlock | entry | key={} | locker={}", name, lockId);

        if (StringUtils.isBlank(lockId) || StringUtils.isBlank(name)) {
            return false;
        }
        String key = lockerKey(name);
        try {
            if (!redisHelper.hasKey(key)) {
                return true;
            }

            String value = redisHelper.getValue(key);
            if (StringUtils.isBlank(value) || value.equals(lockId)) {
                redisHelper.delKey(key);
                return true;
            }
        } catch (Exception ex) {
            log.error("unlock | Failed to unlock | {} | {}", name, lockId, ex);
        }

        return false;
    }

    /**
     * 判断是否还持有锁
     *
     * @param name
     * @param lockId
     * @return
     */
    protected boolean isHoldLock(String name, String lockId) {
        try {
            if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(lockId)) {
                String value = redisHelper.getValue(lockerKey(name));
                if (lockId.equals(value)) {
                    return true;
                }
            }
        } catch (Exception ex) {
            log.error("isHoldLock | Failed | {} | {}", name, lockId, ex);
        }

        return false;
    }

    /**
     * 判断是否被锁住，该方法是保守策略，如果redis异常，则被锁住
     *
     * @param name
     * @return
     */
    protected boolean isLocked(String name) {
        try {
            return redisHelper.hasKey(lockerKey(name));
        } catch (Exception ex) {
            log.error("isLocked | Failed | {}", name, ex);
        }

        return true;
    }

    /**
     * 高级锁定
     *
     * @param name
     * @param timeout
     * @return
     */
    protected String vipLock(String name, int timeout) {
        if (timeout < 1 || timeout > MAX_LOCKER_TIME) {
            log.error("invalid timeout. It must be between 1 and 3600");
            return null;
        }

        if (StringUtils.isBlank(name)) {
            log.error("invalid name. It's blank");
            return null;
        }

        try {
            String key = lockerKey(name);
            String lockId = VIP_LOCK_PREFIX + Utility.simpleUUID();

            String value = redisHelper.getValue(key);
            if (StringUtils.isBlank(value)) {
                if (redisHelper.setNX(key, lockId, timeout)) {
                    return lockId;
                }
                value = redisHelper.getValue(key);
            }

            if (value.startsWith(VIP_LOCK_PREFIX)) {
                return null;
            }

            redisHelper.setValue(key, lockId, timeout);
            return lockId;
        } catch (Exception ex) {
            log.error("lock | Failed to vipLock | {} | {}", name, timeout, ex);
        }

        return null;
    }

    private String lockerKey(String name) {
        return LOCK_PREFIX + name;
    }
}
