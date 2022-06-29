package com.yyd.yyd.constants;


public interface Const {

    String TRACE_ID = "traceId";
    String AUTH_TOKEN = "X-NewAuth-Token";

    interface ValidMsg {
        String COMM_EMPTY = "必填参数为空";
        String USER_GID_EMPTY = "userGid为空";
        String PRODUCT_GID_EMPTY = "productGid为空";
        String PERIOD_GID_EMPTY = "periodGid为空";
        String REPAY_TYPE_EMPTY = "还款方式为空";
        String LOAN_AMOUNT_EMPTY = "借款金额为空";
        String CARD_GID_EMPTY = "cardGid为空";
        String LOAN_GID_EMPTY = "loanGid为空";
        String LOAN_USAGE_EMPTY = "借款用途为空";
        String BORROWER_TYPE_EMPTY = "贷款类型为空";
    }

    interface Grade {
        // 毕业
        Integer graduation = 0;
        //一年级
        Integer firstGrade = 11;
        //二年级
        Integer secondGrade = 12;
        //三年级
        Integer ThirdGrade = 13;
        //四年级
        Integer fourthGrade = 14;
        //五年级
        Integer fiveGrade = 15;
        //六年级
        Integer sixGrade = 16;
        //初一
        Integer juniorOne = 21;
        //初二
        Integer juniorTwo = 22;
        //初三
        Integer juniorThree = 23;
        //初四
        Integer juniorFour = 24;
        //高中预科班
        Integer seniorPrepare = 30;
        //高一
        Integer seniorOne = 31;
        //高二
        Integer seniorTwo = 32;
        //高三
        Integer seniorThree = 33;
    }

    // 班级类型，0是流动班级，1是行政班级
    interface ClassType {
        Integer FLOW = 1;
        Integer ADMINISTRATIO = 0;
    }

    // 文理属性,1是文科,2是理科
    interface ArtScienceAttribute {
        Integer ART = 1;
        Integer SCIENCE = 2;
    }

    // 教育阶段 1小学 2初中 3高中
    interface AcademicSection {
        Integer PRIMARy_SCHOOL = 1;
        Integer JUNIOR_SCHOOL = 2;
        Integer HIGH_SCHOOL = 3;
    }

    // 学期 1春季学期 2秋季学期
     interface Term {
        Integer SPRING = 1;
        Integer AUTUMN = 2;
    }

}
