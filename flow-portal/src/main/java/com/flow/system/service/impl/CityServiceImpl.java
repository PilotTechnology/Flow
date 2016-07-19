package com.flow.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.flow.system.mapper.CityMapper;
import com.flow.system.mapper.ProvinceMapper;
import com.flow.system.model.City;
import com.flow.system.model.Province;
import com.flow.system.service.CityService;

public class CityServiceImpl implements CityService {

	@Autowired
	private CityMapper cityMapper;
	
	@Autowired
	private ProvinceMapper provinceMapper;
	
	@Override
	public City getCityByCode(Integer cityCode) {
		// TODO Auto-generated method stub
		City city = cityMapper.selectByPrimaryKey(cityCode);
		Province province = provinceMapper.selectByPrimaryKey(Integer.parseInt(city.getProvinceCode()));
		city.setProvince(province);
		return city;
	}

}
