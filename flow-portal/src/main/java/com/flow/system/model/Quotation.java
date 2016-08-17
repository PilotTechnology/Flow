package com.flow.system.model;

import java.util.Date;

public class Quotation {
    private Integer id;

    private String serviceCode;

    private String distributorCode;

    private String fatherCode;

    private Integer state;

    private Date createDate;

    private Integer isDisplayProvince;
    
    private String distributionName;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode == null ? null : serviceCode.trim();
    }

    public String getDistributorCode() {
        return distributorCode;
    }

    public void setDistributorCode(String distributorCode) {
        this.distributorCode = distributorCode == null ? null : distributorCode.trim();
    }

    public String getFatherCode() {
        return fatherCode;
    }

    public void setFatherCode(String fatherCode) {
        this.fatherCode = fatherCode == null ? null : fatherCode.trim();
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

    public Integer getIsDisplayProvince() {
        return isDisplayProvince;
    }

    public void setIsDisplayProvince(Integer isDisplayProvince) {
        this.isDisplayProvince = isDisplayProvince;
    }
    
    public String getdistributionName() {
        return distributionName;
    }

    public void setDistributionName(String distributionName) {
        this.distributionName = distributionName == null ? null : distributionName.trim();
    }
}