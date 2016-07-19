package com.flow.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flow.system.mapper.CityMapper;
import com.flow.system.model.City;
import com.flow.system.service.CityService;
@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityMapper cityMapper;
	
	@Override
	public City getCityByCode(Integer cityCode) {
		return cityMapper.selectByPrimaryKey(cityCode);
	}

}
