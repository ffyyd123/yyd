package com.yyd.yyd.utils;

import java.math.BigDecimal;

public class BigDecimalUtils {

    public static String toString(BigDecimal a) {
        if (a == null) {
            return null;
        }

        // 去除多余0 和 避免科学计数法
        return a.stripTrailingZeros().toPlainString();
    }

    /**
     * 乘法
     *
     * @param a
     * @param b
     * @return
     */
    public static BigDecimal multiply(BigDecimal a, BigDecimal b) {
        return a.multiply(b);
    }

    /**
     * 减法
     *
     * @param a
     * @param b
     * @return a-b
     */
    public static BigDecimal subtract(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }


    /**
     * 加法
     *
     * @param a
     * @param b
     * @return a+b
     */
    public static BigDecimal add(BigDecimal a, BigDecimal b) {
        if (a == null) {
            a = BigDecimal.ZERO;
        }
        if (b == null) {
            b = BigDecimal.ZERO;
        }
        return a.add(b);
    }

    public static BigDecimal add(BigDecimal... params) {
        BigDecimal all = BigDecimal.ZERO;
        for (BigDecimal p : params) {
            if (p == null) {
                p = BigDecimal.ZERO;
            }

            all = all.add(p);
        }

        return all;
    }

    /**
     * 判断值是否大于0
     *
     * @param a
     * @return >0 true, else false
     */
    public static boolean isGreaterThanZero(BigDecimal a) {
        if (a == null) {
            return false;
        }

        int i = a.compareTo(BigDecimal.ZERO);
        return i == 1;
    }

    /**
     * 判断大于等于0
     *
     * @param a
     * @return
     */
    public static boolean isGreaterThanOrEqualToZero(BigDecimal a) {
        if (a == null) {
            return false;
        }

        int i = a.compareTo(BigDecimal.ZERO);
        return i >= 0;
    }

    public static boolean isEqualToZero(BigDecimal a) {
        int i = a.compareTo(BigDecimal.ZERO);
        return i == 0;
    }

    /**
     * a >= b
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean isGreaterThanOrEqualTo(BigDecimal a, BigDecimal b) {
        return isGreaterThanOrEqualToZero(subtract(a, b));
    }

    public static boolean isGreaterThan(BigDecimal a, BigDecimal b) {
        return isGreaterThanZero(subtract(a, b));
    }

    public static BigDecimal getDefaultZero(BigDecimal amount) {
        return null == amount ? BigDecimal.ZERO : amount;
    }
}
