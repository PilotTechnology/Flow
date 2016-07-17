package com.flow.pub.common;

public class BaseResponse {
	private String result;
	private String errMsg;

	public BaseResponse() {
		super();
	}

	public BaseResponse(String result, String errMsg) {
		super();
		this.result = result;
		this.errMsg = errMsg;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

}
