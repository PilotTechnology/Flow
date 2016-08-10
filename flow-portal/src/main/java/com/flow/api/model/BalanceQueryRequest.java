package com.flow.api.model;

public class BalanceQueryRequest {
	private String appkey;
	private String time;
	private String sign;  // sign=MD5(time=xxxxxx&secret=xxxxxxxx)

	
	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
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
