package com.flow.pub.common;

public class Constant {
	// JSON二元返回信息中result的值
	public static final String JSON_SUCCESS = "success";
	public static final String JSON_FAIL = "fail";

	public static final BaseResponse successMsg = new BaseResponse(JSON_SUCCESS, "操作成功");
	public static final BaseResponse failMsg = new BaseResponse(JSON_FAIL, "操作失败，请稍后重试！");
	// 重置密码
	public static final String PASSWORD = "123456";
}
