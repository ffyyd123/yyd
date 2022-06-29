package com.yyd.yyd.utils;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


@SuppressWarnings("unchecked")
@Slf4j
@Component
public class Utility {


    private static final Logger logger = LoggerFactory.getLogger(Utility.class);

    public static String simpleUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


    public static String objectToString(Object value) {
        return null == value ? null : String.valueOf(value);
    }

    public static String bigDecimalToString(BigDecimal value) {
        return null == value ? null : String.valueOf(value.setScale(2, BigDecimal.ROUND_DOWN));
    }

    public static String getCacheKey(String suffix) {
        if (StringUtils.isBlank(suffix)) {
            logger.warn("getCacheKey: suffix is null.");
            return null;
        }
        return "parrot:cache:" + suffix;
    }

    public static String getCacheKey(String prefix, String suffix) {
        if (StringUtils.isBlank(prefix) || StringUtils.isBlank(suffix)) {
            logger.warn("getCacheKey: prefix or suffix is null.");
            return null;
        }
        return prefix + ":cache:" + suffix;
    }

    public static String encodeUtf8(String url) {
        try {
            return URLEncoder.encode(url, "utf-8");
        } catch (UnsupportedEncodingException e) {
            logger.warn("encodeUtf8: is fail.{}", e.getMessage());
        }
        return url;
    }

    public static String convertMobileUrl(String url, String userAgent) {
        if (StringUtils.isBlank(userAgent)) {
            return url;
        }
        if (userAgent.contains("iPod") //
                || userAgent.contains("iPhone") //
                || userAgent.contains("iPad") //
                || userAgent.contains("Android") //
                || userAgent.contains("webOS")) {
            if (StringUtils.isNotBlank(url)) {
                url = url.replace("[", "%5B").replace("]", "%5D");
            }
        }
        return url;
    }

    public static Integer getCurrTimestamp() {
        return Math.toIntExact(System.currentTimeMillis() / 1000);
    }


    public static String getAppletSessCKey(String clientTag, String openId) {
        return MD5Utils.md5Code(clientTag + openId);
    }

    public static String getCurrentYear() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        return sdf.format(date);
    }

    /**
     * 将拼接字符串拆为字符串集合
     *
     * @param str   拼装的字符串
     * @param split 以该字符串拆分
     * @return 字符串集合
     */
    public static List<String> convertListByString(String str, String split) {
        log.info("convertListByString str:{}  split:{}", str, split);
        List<String> list;
        if (StringUtils.isBlank(str)) {
            return Arrays.asList();
        }
        if (str.contains(split)) {
            list = Arrays.stream(str.split(split)).collect(Collectors.toList());
        } else {
            list = new ArrayList<>();
            list.add(str);
        }
        return list;
    }

    /**
     * 将String类型的list拼装为字符串
     *
     * @param list   字符串集合
     * @param suffix 拼装时的字符
     * @return 拼装好的字符串
     */
    public static String convertStringByList(List<String> list, String suffix) {
        log.info("convertStringByList list:{}  suffix:{}", list, suffix);
        if (CollectionUtils.isEmpty(list)) {
            return new String();
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i < (list.size() - 1)) {
                builder.append(list.get(i).toString());
                builder.append(suffix);
            } else {
                builder.append(list.get(i));
            }
        }
        return builder.toString();
    }

    /**
     * 将字符串类型的时间转换成时间戳格式
     *
     * @param date   时间戳
     * @param format 需要转的格式
     * @return 字符串时间
     */
    public static String convertTimeByTimeStamp(Long date, String format) {
        log.info("convertTimeByTimeStamp date:{}  format:{}", date, format);
        if (date == null) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String parse;
        parse = simpleDateFormat.format(new Date(date * 1000));
        return parse;
    }

    /**
     * 将字符串类型的时间转换成时间戳格式
     *
     * @param date   字符串时间
     * @param format 需要转的格式
     * @return 时间戳
     */
    public static String convertTimeByString(String date, String format) {
        log.info("convertTimeByString date:{}  format:{}", date, format);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date parse = null;
        try {
            parse = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            log.error("格式错误:{}", date);
        }
        long time = parse.getTime();
        return time + "";
    }

    /**
     * @param str    待截取的字符串
     * @param split  截取字符串的分割符最后一个
     * @param length 长度 需要截取这个分隔符的后几位
     * @return 返回截取的字符串
     */
    public static String subStr(String str, String split, int length) {
        log.info("subStr str:{}  split:{},length:{}", str, split,length);
        if (str == null) {
            return str;
        }
        if (!str.contains(split)) {
            return str;
        }
        return str.substring(str.lastIndexOf(split) + length);
    }

    /**
     * @param startIndex 开始索引
     * @param str        待截取的字符串
     * @param split      截取字符串的分割符
     * @param length     长度 需要截取这个分隔符的后几位
     * @return 返回截取的字符串
     */
    public static String subFrontStr(Integer startIndex, String str, String split, int length) {
        log.info("subFrontStr startIndex:{}  str:{}  split:{},length:{}",startIndex, str, split,length);
        if (str == null) {
            return str;
        }
        if (!str.contains(split)) {
            return str;
        }
        return str.substring(startIndex, str.indexOf(split) + length);
    }

    public static String joinPoint(String... str) {
        return StringUtils.join(str, ".");
    }

    /**
     * 开始分页
     *
     * @param list
     * @param pageNum  页码
     * @param pageSize 每页多少条数据
     * @return
     */
    public static List startPage(List list, Integer pageNum, Integer pageSize) {
        if(list == null){
            return null;
        }
        if(list.size() == 0){
            return new ArrayList();
        }

        Integer count = list.size(); //记录总数
        Integer pageCount = 0; //页数
        if (count % pageSize == 0) {
            pageCount = count / pageSize;
        } else {
            pageCount = count / pageSize + 1;
        }

        int fromIndex = 0; //开始索引
        int toIndex = 0; //结束索引

        if(pageNum > pageCount){
            pageNum = pageCount;
        }
        if (!pageNum.equals(pageCount)) {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = fromIndex + pageSize;
        } else {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = count;
        }

        List pageList = list.subList(fromIndex, toIndex);

        return pageList;
    }

//    public static String buildObject(Object o) {
////        Class<?> aClass = o.getClass();
////        aClass.get
//    }
}
