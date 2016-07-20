package com.flow.system.model;

public class Mobile {
    private Integer id;

    private String mobileCode;

    private Integer cityCode;

    private Integer operatorCode;
    
    private City city;
    
    private MobileOperator mobileOperator;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobileCode() {
        return mobileCode;
    }

    public void setMobileCode(String mobileCode) {
        this.mobileCode = mobileCode == null ? null : mobileCode.trim();
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public Integer getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(Integer operatorCode) {
        this.operatorCode = operatorCode;
    }
    
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
    
    public MobileOperator getMobileOperator() {
        return mobileOperator;
    }

    public void setMobileOperator(MobileOperator mobileOperator) {
        this.mobileOperator = mobileOperator;
    }
    
    @Override
	public String toString() {
		return "City [id=" + id + ", mobileCode=" + mobileCode + ", provinceCode=" + city.getProvinceCode() + ", provinceName=" + city.getProvince().getProvinceName() + ", cityCode="+ cityCode + ", cityName=" + city.getCityName() + ", mobileOperatorCode=" + operatorCode + ", mobileOperatorName," + mobileOperator.getOperatorName();
	}
}