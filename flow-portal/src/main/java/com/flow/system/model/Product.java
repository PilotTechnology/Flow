package com.flow.system.model;

public class Product {
    private Integer id;

    private String productCode;

    private String prodcutName;

    private String operatorCode;

    private String providerCode;

    private String proProductCode;

    private String provinceCode;
    
    private Integer enableArea;

    private Integer enableType;

    private Integer priority;

    private Integer size;

    private Double price;

    private Double discount;

    private Double purchased;

    private Integer state;

    private String description;
    
    private MobileOperator mobileOperator;
    
    private Provider provider;
    
    private Province province;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    public String getProdcutName() {
        return prodcutName;
    }

    public void setProdcutName(String prodcutName) {
        this.prodcutName = prodcutName == null ? null : prodcutName.trim();
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

    public String getProProductCode() {
        return proProductCode;
    }

    public void setProProductCode(String proProductCode) {
        this.proProductCode = proProductCode == null ? null : proProductCode.trim();
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode == null ? null : provinceCode.trim();
    }

    public Integer getEnableArea() {
        return enableArea;
    }

    public void setEnableArea(Integer enableArea) {
        this.enableArea = enableArea;
    }
    
    public Integer getEnableType() {
        return enableType;
    }

    public void setEnableType(Integer enableType) {
        this.enableType = enableType;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
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

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getPurchased() {
        return purchased;
    }

    public void setPurchased(Double purchased) {
        this.purchased = purchased;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
    
    public MobileOperator getMobileOperator() {
        return mobileOperator;
    }

    public void setMobileOperator(MobileOperator mobileOperator) {
        this.mobileOperator = mobileOperator;
    }
    
    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
    
    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }
}