package com.flow.system.model;

import java.util.Date;

public class RefundFlow {
    private Integer id;

    private String distributorName;

    private String providerName;// 数据库实际存放的时providerCode

    private String phone;

    private String orderCode;

    private String prodcutName;

    private Double purchased;

    private Integer providerIsRefund;

    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    public String getProdcutName() {
        return prodcutName;
    }

    public void setProdcutName(String prodcutName) {
        this.prodcutName = prodcutName == null ? null : prodcutName.trim();
    }

    public Double getPurchased() {
        return purchased;
    }

    public void setPurchased(Double purchased) {
        this.purchased = purchased;
    }

    public Integer getProviderIsRefund() {
        return providerIsRefund;
    }

    public void setProviderIsRefund(Integer providerIsRefund) {
        this.providerIsRefund = providerIsRefund;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}