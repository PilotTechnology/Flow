package com.flow.system.model;

import java.util.Date;

public class RechargeFlow {
    private Integer id;

    private String distributorCode;

    private Double balanceBeforeRecharge;

    private Double recharge;

    private Double balanceAfterRecharge;

    private Date createDate;

    private Integer type;

    private String userCode;
    
    private String distributorName;
    
    private String userName;

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
        this.distributorCode = distributorCode == null ? null : distributorCode.trim();
    }

    public Double getBalanceBeforeRecharge() {
        return balanceBeforeRecharge;
    }

    public void setBalanceBeforeRecharge(Double balanceBeforeRecharge) {
        this.balanceBeforeRecharge = balanceBeforeRecharge;
    }

    public Double getRecharge() {
        return recharge;
    }

    public void setRecharge(Double recharge) {
        this.recharge = recharge;
    }

    public Double getBalanceAfterRecharge() {
        return balanceAfterRecharge;
    }

    public void setBalanceAfterRecharge(Double balanceAfterRecharge) {
        this.balanceAfterRecharge = balanceAfterRecharge;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }
    
    public String getDistributorName() {
        return distributorName;
    }

    public void setDistributorName(String distributorName) {
        this.distributorName = distributorName;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}