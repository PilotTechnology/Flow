package com.flow.model;

import com.flow.common.constants.CodeConstants;

/**
 * 响应父类
 * 
 * @author MK
 *
 */
public class BaseResponse {

	public static final BaseResponse SUCCESS = new BaseResponse(CodeConstants.SUCCESS,"成功！");
	public static final BaseResponse SYS_ERR = new BaseResponse(CodeConstants.SUCCESS,"系统错误！");
	
	private String code;
	private String msg;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public BaseResponse(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "AbsBaseResponse [code=" + code + ", msg=" + msg + "]";
	}
}
