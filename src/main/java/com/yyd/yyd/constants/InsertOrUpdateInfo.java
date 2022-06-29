package com.yyd.yyd.constants;

import lombok.Getter;

@Getter
public enum InsertOrUpdateInfo {

    projectid("1"),
    creator("1"),
    editor("1");

    private String defaultValue;

    InsertOrUpdateInfo(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
