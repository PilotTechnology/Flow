package com.flow.system.model;

import java.util.List;

public class Province {
    private Integer id;
    private String provinceName;
    private List<City> cityList;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

	public List<City> getCityList() {
		return cityList;
	}

	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}

	@Override
	public String toString() {
		return "Province [id=" + id + ", provinceName=" + provinceName + ", cityList=" + cityList + "]";
	}
	
	
}