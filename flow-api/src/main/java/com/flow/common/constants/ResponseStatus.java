package com.flow.common.constants;

public enum ResponseStatus {

    /**
     * 系统通用响应代码段 1xxxx
     */
    RESPONSE_SUCCESS(0,                                             "success", "成功"),
    RESPONSE_SYSTEM_ERROR(10001,                                    "System error", "系统错误"),
    RESPONSE_SERVICE_UNAVAILABLE(10002,                             "Service unavailable", "服务暂停"),
    RESPONSE_ILLEGAL_REQUEST(10003,                                 "Illegal request", "非法请求"),
    RESPONSE_REQUEST_API_NOT_FOUND(10005,                           "Request interfaces not found", "接口不存在"),
    RESPONSE_HTTP_METHOD_IS_NOT_SUPORTED_FOR_THIS_REQUEST(10006,    "HTTP method is not suported for this request", "请求的HTTP METHOD不支持，请检查是否选择了正确的POST/GET方式"),
    RESPONSE_IP_REQUESTS_OUT_OF_RATE_LIMIT(10007,                   "IP requests out of rate limit", "IP请求频次超过上限"),
    RESPONSE_USER_REQUESTS_OUT_OF_RATE_LIMIT(10008,                 "User requests out of rate limit", "用户请求频次超过上限"),
    RESPONSE_INTERNAL_ERROR(10009,                                  "internal error",                "创建用户失败"),
    RESPONSE_VERSION_NOT_SUPPORT(10010,                             "Version not support",      "创建用户失败"),
    RESPONSE_SQL_OPERATION_FAILED(10011,                            "SQL operation failed",      "SQL 操作失败"),
    RESPONSE_NULL_PARAMETER(10012,                                  "Null parameter",      "参数为空"),
    RESPONSE_UPLOAD_FILE_ERROR(10013,                               "Upload file error",      "文件上传错误"),
    RESPONSE_PARAMETER_VALIDATION_ERROR(10014,                               "Parameter validation error",      "参数校验错误"),
    RESPONSE_PARAMETER_TYPE_ERROR(10015,                            "Parameter type error",      "参数类型错误"),
    RESPONSE_DATA_OLD_UPDATE_FAILED(10016,                            "Data old, update failed ",    "更新错误，请刷新后重试"),
    RESPONSE_SMS_CAPTCHA_ERROR(10017,                               "SMS captcha error",      "短信验证码输入错误"),
    RESPONSE_SMS_TEMPLATE_CODE_ERROR(10018,                         "SMS template code error",      "短信模板代码错误"),
    RESPONSE_SMS_TEMPLATE_NOT_EXIST(10019,                              "SMS template not exist",      "短信模板不存在"),
    RESPONSE_SMS_TEMPLATE_PARMAS_ERROR(10020,                       "SMS template parmas error",      "短信模板变量错误"),
    RESPONSE_SMS_SYSTEM_ERROR(10021,                                "SMS system error",      "短信系统发生错误"),
    RESPONSE_IMAGE_CAPTCHA_ERROR(10022,                             "Image captcha error",      "图片验证码输入错误"),
    RESPONSE_UPLOAD_FILE_NOEXTENTION(10023,                         "Upload file error",      "上传文件没有扩展名"),
    RESPONSE_DELETE_FILE_ERROR(10024,                               "Delete file error",      "删除文件错误"),
    RESPONSE_MISSING_PAGE_PARAM(10025,"missing pager parameters","缺少分页参数"),
    /**
     * 用户响应代码段 2xxxx
     */
    RESPONSE_USER_ALREADY_EXIST(20001,                              "User already exist",        "用户已经存在"),
    RESPONSE_USER_DOES_NOT_EXISTS(20002,                            "User does not exists",    "用户不存在"),
    RESPONSE_UID_PARAMETER_IS_NULL(20003,                           "Uid parameter is null",  "Uid参数为空"),
    RESPONSE_USER_TOKEN_IS_ERROR(20004,                             "User token is error", "参数为空"),
    RESPONSE_UNKOWN_REGISTER_TYPE(20005,                            "unkown register type", "未知注册类型"),
    RESPONSE_CREATE_USER_FAIL(20006,                                "Create user Fail", "创建用户失败"),
    RESPONSE_NOT_VALID_EMAIL_OR_PHONE(20007,                        "not valid email or phone", "非法的手机或邮箱"),
    RESPONSE_PASSWORD_NOT_CORRECT(20008,                            "password not correct", "密码不正确"),
    RESPONSE_USER_NAME_ALREADY_EXIST(20009,                         "user name already exist", "用户名已经存在"),
    RESPONSE_VERIFY_CODE_NOT_CORRECT(20010,                         "verify code not correct", "验证码不正确"),
    RESPONSE_SNS_ALREADY_BIND(20011,                                "sns already bind", "账号已经绑定"),
    RESPONSE_SNS_BIND_FAIL(20012,                                   "sns bind fail", "sns bind fail"),
    RESPONSE_SNS_NOT_BOUND(20013,                                   "sns not bound", "sns not bound"),
    RESPONSE_CANNOT_UNBIND_WITH_REGISTER_ACCOUNT(20014,             "cannot unbind with register account", "sns not bound"),
    RESPONSE_NO_USER_SKIN_IMAGE(20015,                              "no user skin image", "用户皮肤不存在"),
    RESPONSE_NO_PHONE(20016,                                        "no phone", "电话号码为空"),
    RESPONSE_NOT_VERIFIED(20017,                                    "not verified", "电话号码未验证"),
    RESPONSE_PHONE_ALREADY_BOUND(20018,                             "phone already bound", "电话号码已经绑定"),
    RESPONSE_WEIBO_TOKEN_EXPIRED(20019,                             "weibo token expired", "微博token过期"),
    RESPONSE_FOLLOWING_NUMBER_LIMIT_REACH(20020,                    "following number limit reach", "达到关注上限"),
    RESPONSE_USER_NAME_ILLEGAL(20021,                               "user name illegal", "用户名非法"),
    RESPONSE_VERIFY_CODE_EXPIRED(20022,                             "verify code has expired", "验证码已经过期"),
    RESPONSE_USER_CERT_TYPE_ERROR(20023,                            "dict user cert not exits",      "证件类型错误"),
    RESPONSE_USER_MANAGER_CODE_ERROR(20024,                         "user code error",      "用户唯一编码异常"),
    RESPONSE_USER_MANAGER_CODE_RANGE_ERROR(20025,                   "manager user code range error",      "管理员不能大于9人"),
    RESPONSE_VERIFY_CODE_NULL(20026,                                "verify code is null",      "短信验证码为空"),
    RESPONSE_PASSWORD_NULL(20027,                                   "password is null",      "密码为空"),
    RESPONSE_INVITE_CODE_ERROR(20028,                               "invite code error",      "邀请码错误"),
    RESPONSE_USER_SAFE_PHONE_ERROR(20029,                               "user safe phone error",      "安全手机号错误"),
    RESPONSE_USER_SAFE_EMAIL_ERROR(20030,                               "user safe email error",      "安全邮箱错误"),
    RESPONSE_CHECK_IS_NULL_ERROR(20031,                               "check type is undefined",      "检查类型没有定义"),
    RESPONSE_PLAT_USER_TRADE_PASSWORD_EXITS(20032,                  "user trade password exits.",      "提现密码已存在，请偿试修改提现密码"),
    RESPONSE_PLAT_USER_TRADE_PASSWORD_NOT_EXITS(20033,              "user trade password not exits.",      "提现密码不存在，请偿试先设置提现密码"),
    RESPONSE_WEALTH_ORG_INVALID(20034,              "wealth organization is invalid.",      "用户所属法人机构或财富机构不是生效状态"),
    RESPONSE_WEALTHUSER_NO_ROLE(20035,              "wealth user has no role.",      "用户没有角色，所属法人机构未进行权限配置"),
    RESPONSE_MOBILE_NO_FORMAT_ERROR(20036,                              "mobile no format error",      "手机号码格式错误"),
    RESPONSE_ID_CARD_NO_FORMAT_ERROR(20037,                             "ID card no format error",      "身份证号码格式错误"),
    /**
     * 字典业务响应代码段 4xxxx
     */
    RESPONSE_UNKOWN_FOLDER_TYPE(40001,                              "unkown folder type", "未知夹子类型"),
    RESPONSE_FOLDER_NOT_FOUND(40002,                                "folder not found", "未找到夹子"),
    RESPONSE_CITY_NOT_FOUND(40003,                                  "city not found", "城市未找到"),
    RESPONSE_PLACE_NOT_FOUND(40004,                                 "place not found", "地点未找到"),
    RESPONSE_CLUSTER_NOT_FOUND(40005,                               "cluster not found", "地点簇未找到"),
    RESPONSE_ID_NOT_FOUND(40006,                                    "id not found", "id未找到"),
    RESPONSE_NO_POLYGON(40007,                                      "no polygon", "no polygon"),

    /**
     * 移动端通用响应代码段 5xxxx
     */
    RESPONSE_UNKOWN_PHONE_TYPE(50001,                               "unkown phone type", "未知手机类型"),
    RESPONSE_UNKOWN_TOKEN_TYPE(50002,                               "unkown token type", "未知token类型"),
    RESPONSE_PUSH_TOKEN_PARAMETER_NOT_COMPLETE(50003,               "push token parameter not complete", "参数不全"),
    RESPONSE_UID_NOT_PROVIDED(50004,                                "uid not provided", "uid为空 "),

    /**
     * 权限响应代码段 6xxxx
     */
    RESPONSE_SESSION_TIMEOUT(60001,"session time out ","登录状态失效"),
    RESPONSE_NO_AUTHORIZATION(60002,"no authorization","没有访问权限"),
    RESPONSE_ILLEGAL_ACCESS(60003,"illegal access","非法请求"),
    RESPONSE_PASSWORD_DIFFERENT(60004,"password different","两次输入的新密码不一致"),
    RESPONSE_UID_NULL(60005,"uid null","用户id为空"),
    RESPONSE_CURRENTPASSWORD_ERROR(60006,"aurrent password error","当前密码不正确"),
    RESPONSE_USERBASEINFO_NULL(60007,"user base info null","用基本信息未找到"),
    RESPONSE_PICTUREVALIDATIONCODE_ERROR(60008,"picture validation code error","图片验证码错误"),
    RESPONSE_SHORTMESSAGEVALIDATIONCODE_ERROR(60009,"short message validation code error","短信验证码错误"),
    RESPONSE_USER_NO_ROLE_ERROR(60010,"user no role error","用户未分配角色"),
    RESPONSE_WEALTHUSER_STATUS_ERROR(60011,"wealthUser status error","财富管理人为冻结/离岗状态"),
    RESPONSE_ADMIN_STATUS_ERROR(60012,"admin status error","管理员为离岗状态"),
    RESPONSE_EMAILVALIDATIONCODE_ERROR(60013,"short message validation code error","邮箱验证码错误");

    public int code;

    public String englishMessage;

    public String chineseMessage;

    ResponseStatus(int code,String englishMessage,String chineseMessage){
        this.code = code;
        this.englishMessage = englishMessage;
        this.chineseMessage = chineseMessage;
    }


}
