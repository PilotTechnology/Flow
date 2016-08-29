package com.flow.api.model;

import com.flow.pub.common.CodeConstants;

/**
 * 响应父类
 * 
 * @author MK
 *
 */
public class OrderResponse {

	public static final OrderResponse SUCCESS = new OrderResponse(CodeConstants.SUCCESS,"成功！");
	public static final OrderResponse SYS_ERR = new OrderResponse(CodeConstants.SUCCESS,"系统错误！");
	
	private String code;
	private String order_id;
	private String p_order_id;
	private String msg;
	
	public String getCode() {
		return code;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	public String getOrder_id() {
		return order_id;
	}
	
	public void setP_order_id(String p_order_id) {
		this.p_order_id = p_order_id;
	}
	
	public String getP_order_id() {
		return p_order_id;
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

	public OrderResponse(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "AbsBaseResponse [code=" + code + ", msg=" + msg + "]";
	}
}
