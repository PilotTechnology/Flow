package com.flow.api.model;

import com.flow.pub.common.CodeConstants;

public class OrderQueryResponse {
	public static final OrderQueryResponse SUCCESS = new OrderQueryResponse(CodeConstants.SUCCESS,"成功！");
	public static final OrderQueryResponse SYS_ERR = new OrderQueryResponse(CodeConstants.SUCCESS,"系统错误！");
	
	private String code;
	private String msg;
	private String orderId;
	private String pOrderId;
	
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

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public String getPOrderId() {
		return pOrderId;
	}

	public void setPOrderId(String pOrderId) {
		this.pOrderId = pOrderId;
	}

	public OrderQueryResponse(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "AbsBaseResponse [code=" + code + ", msg=" + msg + ", orderId=" + orderId + ",pOrderId=" + pOrderId + "]";
	}
}
