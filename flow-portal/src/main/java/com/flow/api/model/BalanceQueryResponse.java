package com.flow.api.model;

import com.flow.pub.common.CodeConstants;

public class BalanceQueryResponse {
	public static final BalanceQueryResponse SUCCESS = new BalanceQueryResponse(CodeConstants.SUCCESS,"成功！");
	public static final BalanceQueryResponse SYS_ERR = new BalanceQueryResponse(CodeConstants.SUCCESS,"系统错误！");
	
	private String code;
	private String msg;
	private Double balance;
	
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

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public BalanceQueryResponse(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "AbsBaseResponse [code=" + code + ", msg=" + msg + ", balance=" + balance + "]";
	}
}
