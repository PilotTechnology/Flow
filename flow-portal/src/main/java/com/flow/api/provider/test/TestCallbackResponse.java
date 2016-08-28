package com.flow.api.provider.test;

public class TestCallbackResponse {
	
	public static final TestCallbackResponse SUCCESS = new TestCallbackResponse("0000");
	public static final TestCallbackResponse SYS_ERR = new TestCallbackResponse("0001");
	
	private String code;
	
	public TestCallbackResponse(String code) {
		super();
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
