package com.flow.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.flow.system.mapper.CityMapper;
import com.flow.system.model.City;
import com.flow.system.service.CityService;

public class CityServiceImpl implements CityService {

	@Autowired
	private CityMapper cityMapper;
	
	@Override
	public City getCityByCode(Integer cityCode) {
		// TODO Auto-generated method stub
		return cityMapper.selectByPrimaryKey(cityCode);
	}

}
