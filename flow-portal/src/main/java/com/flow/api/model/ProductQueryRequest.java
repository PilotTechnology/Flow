package com.flow.api.model;

public class ProductQueryRequest {
	private String appkey;
	private String operator;
	private String time;
	private String sign;  // sign=MD5(operator=x&time=xxxxxx&secret=xxxxxxxx)

	
	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}
	
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
}
