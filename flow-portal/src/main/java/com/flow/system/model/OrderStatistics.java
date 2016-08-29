package com.flow.system.model;

public class OrderStatistics {

	private String distributorCode;

    private String distributorName;

    private Integer size;
    
    private String province;
    
    private Integer successNum;

    private Integer failNum;
    
    private Double moneyOfSuccess;
    
    private Double percent;
    
    private String day;

    public String getDistributorCode() {
        return distributorCode;
    }
    
    public void setDistributorCode(String distributorCode) {
        this.distributorCode = distributorCode == null ? null : distributorCode.trim();
    }

    public void setDistributorName(String distributorName) {
        this.distributorName = distributorName == null ? null : distributorName.trim();
    }
    
    public String getDistributorName() {
        return distributorName;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
    
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }
    
    public String getProvince() {
        return province;
    }
    
    public Integer getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(Integer successNum) {
        this.successNum = successNum;
    }
    
    public Integer getFailNum() {
        return failNum;
    }

    public void setFailNum(Integer failNum) {
        this.failNum = failNum;
    }

    public Double getMoneyOfSuccess() {
        return moneyOfSuccess;
    }

    public void setMoneyOfSuccess(Double moneyOfSuccess) {
        this.moneyOfSuccess = moneyOfSuccess;
    }
    
    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }
    
    public String getDay() {
        return day;
    }
    
    public void setDay(String day) {
        this.day = day == null ? null : day.trim();
    }
}