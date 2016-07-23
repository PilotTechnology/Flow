package com.flow.system.bean;

import java.util.Date;

/**
 * 分销平台订单业务实体类
 * @author MK
 * @date 2016-07-23
 *
 */
public class OrderBean {

	private Integer id ;	//序号
	private String distributorCode;;//用户编号
	private String providerCode;//供应商编号
	private String providerName;//供应商名称
	private String orderCode;		 //平台订单号
	private String providerOrderCode;//上游订单编号
	private String distributorOrderCode;//下游订单编号
	private String operatorCode;		//运营商编号	
	private String operatorName;		//运营商名称
	private String provinceName;		//省份名称
	private String phone;				//手机号
	private Integer size;				//订单购买流量包大小(M)
	private Double price;				//订单实际购买价格
	private Integer discount;			//实际折扣
	private Integer state;				//订单状态 0 ：处理中 1：充值成功 2：充值失败
	private Date	createDate;			//创建时间
	private Date 	callBackDate;		//回调时间
	private String  callBackCode;		//回调响应码
	private String  callBackCodeMess;	//回调响应描述
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDistributorCode() {
		return distributorCode;
	}
	public void setDistributorCode(String distributorCode) {
		this.distributorCode = distributorCode;
	}
	public String getProviderCode() {
		return providerCode;
	}
	public void setProviderCode(String providerCode) {
		this.providerCode = providerCode;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getProviderOrderCode() {
		return providerOrderCode;
	}
	public void setProviderOrderCode(String providerOrderCode) {
		this.providerOrderCode = providerOrderCode;
	}
	public String getDistributorOrderCode() {
		return distributorOrderCode;
	}
	public void setDistributorOrderCode(String distributorOrderCode) {
		this.distributorOrderCode = distributorOrderCode;
	}
	public String getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getCallBackDate() {
		return callBackDate;
	}
	public void setCallBackDate(Date callBackDate) {
		this.callBackDate = callBackDate;
	}
	public String getCallBackCode() {
		return callBackCode;
	}
	public void setCallBackCode(String callBackCode) {
		this.callBackCode = callBackCode;
	}
	public String getCallBackCodeMess() {
		return callBackCodeMess;
	}
	public void setCallBackCodeMess(String callBackCodeMess) {
		this.callBackCodeMess = callBackCodeMess;
	}
	@Override
	public String toString() {
		return "OrderBean [id=" + id + ", distributorCode=" + distributorCode + ", providerCode=" + providerCode
				+ ", providerName=" + providerName + ", orderCode=" + orderCode + ", providerOrderCode="
				+ providerOrderCode + ", distributorOrderCode=" + distributorOrderCode + ", operatorCode="
				+ operatorCode + ", operatorName=" + operatorName + ", provinceName=" + provinceName + ", phone="
				+ phone + ", size=" + size + ", price=" + price + ", discount=" + discount + ", state=" + state
				+ ", createDate=" + createDate + ", callBackDate=" + callBackDate + ", callBackCode=" + callBackCode
				+ ", callBackCodeMess=" + callBackCodeMess + "]";
	}
	
}
