package com.flow.model;

public class Distributor {
    private Integer id;

    private String distrbutorCode;

    private String userCode;

    private String operatorCode;

    private Integer channelType;

    private Integer coopModel;

    private String company;

    private Double balance;

    private Double freezing;

    private Double total;

    private String smsContent;

    private Integer state;

    private Integer level;

    private String callbackUrl;

    private String confiningIp;

    private String appKey;

    private String secretKey;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDistrbutorCode() {
        return distrbutorCode;
    }

    public void setDistrbutorCode(String distrbutorCode) {
        this.distrbutorCode = distrbutorCode == null ? null : distrbutorCode.trim();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode == null ? null : operatorCode.trim();
    }

    public Integer getChannelType() {
        return channelType;
    }

    public void setChannelType(Integer channelType) {
        this.channelType = channelType;
    }

    public Integer getCoopModel() {
        return coopModel;
    }

    public void setCoopModel(Integer coopModel) {
        this.coopModel = coopModel;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getFreezing() {
        return freezing;
    }

    public void setFreezing(Double freezing) {
        this.freezing = freezing;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent == null ? null : smsContent.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl == null ? null : callbackUrl.trim();
    }

    public String getConfiningIp() {
        return confiningIp;
    }

    public void setConfiningIp(String confiningIp) {
        this.confiningIp = confiningIp == null ? null : confiningIp.trim();
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey == null ? null : appKey.trim();
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey == null ? null : secretKey.trim();
    }
    
}