package com.yyd.yyd.frame.rabbit;


import com.yyd.yyd.utils.JsonUtils;


public enum MessageType {

    REPORT_NEW_USER("REPORT_NEW_USER", "上报大数据-新增用户"),
    REPORT_BIND_CARD("REPORT_BIND_CARD", "上报大数据-添加银行卡"),
    REPORT_DEL_CARD("REPORT_DEL_CARD", "上报大数据-银行卡删除"),
    REPORT_LOAN_ORDER("REPORT_LOAN_ORDER", "上报大数据-借款上报"),
    REPORT_REPAY_ORDER("REPORT_REPAY_ORDER", "上报大数据-还款操作"),
    ;

    private final String type;
    private final String desc;

    MessageType(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public boolean isThis(MessageType msgType) {
        if (msgType == null) {
            return false;
        }

        for (MessageType type : MessageType.values()) {
            if (type.type.equals(msgType.type)) {
                return true;
            }
        }

        return false;
    }

    public String getType() {
        return this.type;
    }

    public String getDesc() {
        return this.desc;
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}
