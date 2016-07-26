package com.flow.model;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
	private Integer id;
	private String orderCode;				//接口1写值>> 平台订单编号
	private String providerOrderCode;
	private String distributorOrderCode;	//接口1写值>> 下游订单编号
	private String operatorCode;
	private String providerCode;
	private String distributorCode;			//接口1写值>> 下游编号
	private String phone;					//接口1写值>> 平台订单编号
	private Integer size;					
	private BigDecimal price;
	private Integer discount;
	private BigDecimal purchased;
	private BigDecimal realPurchased;
	private Integer realDiscount;
	private Integer servProdId;
	private Integer state;
	private Date createDate;
	private Date callbackDate;
	private String callbackCode;
	private String callbackCodeMess;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode == null ? null : orderCode.trim();
	}

	public String getProviderOrderCode() {
		return providerOrderCode;
	}

	public void setProviderOrderCode(String providerOrderCode) {
		this.providerOrderCode = providerOrderCode == null ? null : providerOrderCode.trim();
	}

	public String getDistributorOrderCode() {
		return distributorOrderCode;
	}

	public void setDistributorOrderCode(String distributorOrderCode) {
		this.distributorOrderCode = distributorOrderCode == null ? null : distributorOrderCode.trim();
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode == null ? null : operatorCode.trim();
	}

	public String getProviderCode() {
		return providerCode;
	}

	public void setProviderCode(String providerCode) {
		this.providerCode = providerCode == null ? null : providerCode.trim();
	}

	public String getDistributorCode() {
		return distributorCode;
	}

	public void setDistributorCode(String distributorCode) {
		this.distributorCode = distributorCode == null ? null : distributorCode.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public BigDecimal getPurchased() {
		return purchased;
	}

	public void setPurchased(BigDecimal purchased) {
		this.purchased = purchased;
	}

	public BigDecimal getRealPurchased() {
		return realPurchased;
	}

	public void setRealPurchased(BigDecimal realPurchased) {
		this.realPurchased = realPurchased;
	}

	public Integer getRealDiscount() {
		return realDiscount;
	}

	public void setRealDiscount(Integer realDiscount) {
		this.realDiscount = realDiscount;
	}

	public Integer getServProdId() {
		return servProdId;
	}

	public void setServProdId(Integer servProdId) {
		this.servProdId = servProdId;
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

	public Date getCallbackDate() {
		return callbackDate;
	}

	public void setCallbackDate(Date callbackDate) {
		this.callbackDate = callbackDate;
	}

	public String getCallbackCode() {
		return callbackCode;
	}

	public void setCallbackCode(String callbackCode) {
		this.callbackCode = callbackCode == null ? null : callbackCode.trim();
	}

	public String getCallbackCodeMess() {
		return callbackCodeMess;
	}

	public void setCallbackCodeMess(String callbackCodeMess) {
		this.callbackCodeMess = callbackCodeMess == null ? null : callbackCodeMess.trim();
	}
}