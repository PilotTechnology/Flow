package com.flow.system.model;

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
	private String phoneProvince;
	private String phoneCity;
	private String productCode;
	private Integer size;					
	private BigDecimal price;
	private BigDecimal discount;
	private BigDecimal purchased;
	private BigDecimal realPurchased;
	private BigDecimal realDiscount;
	private Integer state;
	private String platformErrorCode;
	private Integer noticeState;
	private Integer errorTime;
	private Date createDate;
	private Date callbackDate;
	private String callbackCode;
	private String callbackCodeMess;
	
	private String operatorName;
	private String distributorName;
	private String providerName;
	private Integer enableArea;

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
	
	public String getPhoneProvince() {
		return phoneProvince;
	}

	public void setPhoneProvince(String phoneProvince) {
		this.phoneProvince = phoneProvince == null ? null : phoneProvince.trim();
	}
	
	public String getPhoneCity() {
		return phoneCity;
	}

	public void setPhoneCity(String phoneCity) {
		this.phoneCity = phoneCity == null ? null : phoneCity.trim();
	}
	
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode == null ? null : productCode.trim();
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

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
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

	public BigDecimal getRealDiscount() {
		return realDiscount;
	}

	public void setRealDiscount(BigDecimal realDiscount) {
		this.realDiscount = realDiscount;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	public void setPlatformErrorCode(String platformErrorCode) {
		this.platformErrorCode = platformErrorCode;
	}

	public String getPlatformErrorCode() {
		return platformErrorCode;
	}
	
	public Integer getNoticeState() {
		return noticeState;
	}

	public void setNoticeState(Integer noticeState) {
		this.noticeState = noticeState;
	}
	
	public Integer getErrorTime() {
		return errorTime;
	}

	public void setErrorTime(Integer errorTime) {
		this.errorTime = errorTime;
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
	
	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName == null ? null : operatorName.trim();
	}
	
	public String getDistributorName() {
		return distributorName;
	}

	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName == null ? null : distributorName.trim();
	}
	
	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName == null ? null : providerName.trim();
	}
	
	public Integer getEnableArea() {
		return enableArea;
	}

	public void setEnableArea(Integer enableArea) {
		this.enableArea = enableArea;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderCode=" + orderCode + ", providerOrderCode=" + providerOrderCode
				+ ", distributorOrderCode=" + distributorOrderCode + ", operatorCode=" + operatorCode
				+ ", providerCode=" + providerCode + ", distributorCode=" + distributorCode + ", phone=" + phone
				+ ", phoneProvince=" + phoneProvince + ", phoneCity=" + phoneCity + ", size=" + size + ", price="
				+ price + ", discount=" + discount + ", purchased=" + purchased + ", realPurchased=" + realPurchased
				+ ", realDiscount=" + realDiscount + ", state=" + state + ", platformErrorCode=" + platformErrorCode
				+ ", noticeState=" + noticeState + ", errorTime=" + errorTime + ", createDate=" + createDate
				+ ", callbackDate=" + callbackDate + ", callbackCode=" + callbackCode + ", callbackCodeMess="
				+ callbackCodeMess + ", operatorName=" + operatorName + ", distributorName=" + distributorName
				+ ", providerName=" + providerName + "]";
	}
	
}