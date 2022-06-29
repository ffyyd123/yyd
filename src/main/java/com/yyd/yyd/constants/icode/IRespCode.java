package com.yyd.yyd.constants.icode;


public interface IRespCode {

    String getCode();

    String getMessage();

    String getFormatMessage(String formatMessage);

    String getFormattedMessage(Object... args);
}
