package com.yyd.yyd.constants;

import lombok.Data;

@Data
public class GlobalConstants {

    public static final String schoolSystemProfile = "school.system.";
    public static final String educationStageProfile = "education.stage.";
    public static final String schoolGradeProfile = "school.grade.code.";

    public static final String RESOURCE_PREFIX = "/profile";

    public static final String earlyWarningUpCourseNum = "early.warning.up.course.num";

    public static final String earlyWarningCourseTime = "early.warning.course.time";
    //客户端适用用户范围
    public static final String webUserRange = "web.user.range";

    //客户端类型
    public static final String webType = "web.type";

    //应用类型
    public static final String appTypeOrServiceScene = "app.type.";

    //服务场景
    public static final String serviceScene = "service.scene.";

    public static final Integer weekTime = 60 * 60 * 24 * 7;

    /**
     * 时区配置
     */
    public static final String CONFIG_TIME_ZONE = "config.time.zone";

    public static final String courseTypeProfile = "course.Type.code.";

    //教育云请求头拼接字符串
    public static final String token_prefix = "Basic ";

    public static final String bearer_token_prefix = "Bearer ";
}
