package com.yyd.yyd.constants.icode;

import com.yyd.yyd.utils.JsonUtils;
import com.yyd.yyd.utils.file.FileUploadUtils;
import org.apache.commons.lang3.StringUtils;


public enum RestRespCode implements IRespCode {

    SUCCESS("0000", "成功"),
    PARAMS_ERROR("0001", "填写信息有误"),
    AUTH_ERROR("0002", "认证失败"),
    LOGIN_TIMEOUT("0003", "登录超时"),
    ERROR("500", "失败"),
    //0110:项目管理
    PROJECT_REPEAT("0110", "项目Id不唯一"),
    PROJECT_ID_NOT_NULL("0111", "项目Id不为空"),
    PERMISSION_ID_NOT_NULL("0112", "权限Id不为空"),

    //0120:数据源管理
    CLASS_NOT_FOUND("0120", "找不到驱动类"),
    DATASOURCE_CONNECTION_EXCEPTION("0121", "数据源连接失败"),
    DATASOURCE_ID_NOT_NULL("0122", "数据源id不为空"),

    //0130:数据标准
    DATA_STANDARD_NUMBER_REPEAT("0130", "标准号不唯一"),
    DATA_STANDARD_INSERT_METADATA_REPEAT("0131", "技术元数据英文代码不唯一"),

    //0140:数据质量

    //0150:数据编目
    DATA_NULL("9994", "数据为空"),
    NAME_REPEAT("9996", "名称重复"),
    SYS_ERROR_FREQUENTLY_REQ("9998", "请求频繁"),
    SYS_ERROR("9999", "系统异常"),
    HAVA_CATALOG_INFO("9997", "编目库下的编目表未删除"),
    HAVE_RELATIONSHIP("9995", "存在质量约束不可删除"),
    ALREADY_NOW_TIME("9993","已设置时间区间"),
    ZERO_NOT("9991","数据不能为0"),
    SMALL_MATH("9992","数据不能为小数"),
    //教学日历
    NOT_IN_TIME("1000", "不在教学日历填报时间段"),
    NO_YEAR_OR_TYPE("1001", "没有学年信息或学期类型"),
    TIME_OLD_AFTER_NEW("1002", "结束时间区间必须在开始时间区间之后"),
    GRADE_YEAR_TYPE("1003","已经存在该年级在该学年学期对应的日历"),
    START_TIME_END_TIME("1004","请选择开始时间区间/结束时间区间"),
    CALENDAR_ID_IS_NULL("1005","编辑日历ID为空"),

    //0000是为了防止第一开始就没有数据，导致弹窗不出现
    NOT_HAVE_TEACH_CALENDAR("1006","当前年级在当前年份没有学历数据，请添加后再查询"),

    //审核
    REVIEWED("1100", "已经审核过了"),

    //学校相关
    SCHOOL_NOT_EXIST("1200","该学校不存在"),

    //课程相关
    COURSE_IS_EXIST("1300","当前没有选中课程"),
    ADD_COURSE_SCHOOLSTYSTEM("1301","请选择学段"),

    //学校开课配置
    OPEN_COURSE_DELETE_ERROR("1400","学校开课配置删除失败"),

    //教师任课能力
    TEACHER_COURSE_ABILITY("1500","教师任课能力删除失败"),
    TEACHER_COURSE_REPEAT("1501","教师任课能力重复"),

    //教师身份
    TEACHER_IDENTITY_DELETE_ERROR("1600","教师身份删除失败"),

    //课程表
    COURSE_TABLE_ADD_ERR0R("1700","课程表添加失败"),
    COURSE_CONFIG_ALREADY("1701", "课程已经配置过了"),
    COURSE_CONFIG_DELETE_ERROR("1702", "课程表删除失败"),
    FLOW_CLASS_COURSE_DELETE_ERROR("1703", "删除流动班课程配置的教师id和课程ID不能同时为空"),

    //0200 班级相关异常
    DATA_DEFECT("0200", "参数缺失"),
    UPDATE_FAIL("0201", "更新失败"),
    SAVE_FAIL("0202", "报存失败"),
    CLASS_STATUS("0203", "获取开放锁定班级管理失败"),
    CLASS_STATUS_ERROR("0204", "开放锁定班级管理状态异常"),
    CLASSROOM_STATUS("0205","存在关联班级或者下层关联,无法删除或停用"),
    TEACHER_COURSE("0206","您已经在该年级有了这个课程"),
    NOT_HAVE_BUILDING("0207","暂时没有楼栋楼层房间"),
    CLASS_GRADUATION("0208","所在班级已经毕业"),
    CLASS_EMPTY("0209","班级名称为空"),
    CLASS_REPEAT("0210","相同年级流动班级名称重复"),

    // 0300:文件相关
    FILE_NAME_EXCEEDED_LIMIT_LENGTH("0300", "文件名超出限制长度:" + FileUploadUtils.DEFAULT_FILE_NAME_LENGTH),
    FILE_SIZE_EXCEEDED_LIMIT("0301", "文件超出限制大小:" + FileUploadUtils.DEFAULT_MAX_SIZE + "MB"),
    FILE_EXTENSION_INVALID("0302", "文件后缀名不合法"),
    FILE_UPLOAD_ERROR("0303", "文件上传失败"),
    FILE_DOWNLOAD_ERROR("0304", "文件下载失败"),
    FILE_NOT_EXIST("0305", "文件不存在"),

    //0400  手机号验证码相关
    MESSAGE_CODE_ERROR("0401","验证码错误，请输入正确验证码"),
    MESSAGE_CODE_TIME_LONG("0402","验证码超时,请尝试重新获取"),
    ID_CARD_ERROR("0403","身份证格式错误"),

    EXIST_PHONE("0403","手机号以及存在，请更换手机号重试"),

    APP_WEB_EXIST("0600","添加客户端类型重复"),

    NO_DATA("0160","没有数据"),
    NO_TIME_YEAR("0107","该学年或学期未设置时间区间"),

    LOOK_HISTORY_NOT_HAVE("0500","暂无线上版本，请在下方提交");

    private final String code;
    private final String message;

    RestRespCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getFormatMessage(String formatMessage) {
        if (StringUtils.isBlank(formatMessage)) {
            return message;
        }

        return message + ": " + formatMessage;
    }

    @Override
    public String getFormattedMessage(Object... args) {
        return String.format(message, args);
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}
