package com.flow.api.model;

/**
 * 下游订单请求参数
 * @author MK
 *
 */
public class OrderRequest {

	private String appkey;
	private String phone;
	private int product_id;	//流量包大小为流量大小以M为单位，1G为1024
	private String scope; //适用范围
	private String order_id;
	private String time;
	private String sign;  // sign=MD5(phone=xx&product_id=xx&&ordere_id=xx&time=xx&secret=xx)
	
	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	
	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
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
