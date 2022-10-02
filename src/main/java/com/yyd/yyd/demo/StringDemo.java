package com.yyd.yyd.demo;

import com.yyd.yyd.utils.StringUtils;

import java.util.StringTokenizer;

public class StringDemo {

    public static void main(String[] args) {
        String s = "ss-33-ff-ff-cc";
        String spilt = "-";
        String[] ss = util(s, spilt);
        for (String s1 : ss) {
            System.out.print(s1 + "   ");
        }
        System.out.println("");
        String[] sss = util2(s, spilt);
        for (String sss1 : sss) {
            System.out.print(sss1 + "  ");
        }

        String fanZhuanString = sFanzhuanUtil(s);
        System.out.println(fanZhuanString);
    }

    private static String sFanzhuanUtil(String s) {
        char[] chars = s.toCharArray();
        String ss="";
        for (int i = chars.length-1; i >=0; i--) {
            ss+=chars[i];
        }
        return ss;

    }


    private static String[] util2(String s, String spilt) {
        s.split(spilt);
        StringTokenizer stringTokenizer = new StringTokenizer(s, spilt);
        int i = stringTokenizer.countTokens();
        String[] returnS = new String[i];
        int count = 0;
        while (stringTokenizer.hasMoreTokens()) {
            returnS[count] = stringTokenizer.nextToken();
            count++;
        }
        return returnS;
    }

    private static String[] util(String s, String spilt) {
        String[] returnS = new String[s.length()];
        if (!StringUtils.isEmpty(s)) {
            char[] chars = s.toCharArray();
            String sc = "";
            for (int i = 0; i < chars.length; i++) {
                if (!String.valueOf(chars[i]).equals(spilt)) {
                    sc += chars[i];
                } else {
                    returnS[i] = sc;
                    sc = "";
                    continue;
                }

            }
        }
        return returnS;

    }
}
