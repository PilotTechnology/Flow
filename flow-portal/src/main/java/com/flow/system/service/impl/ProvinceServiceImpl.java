package com.flow.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.flow.system.mapper.ProvinceMapper;
import com.flow.system.model.Province;
import com.flow.system.service.ProvinceService;

public class ProvinceServiceImpl implements ProvinceService {

	@Autowired
	private ProvinceMapper provinceMapper;
	
	@Override
	public Province getProvinceByCode(Integer provinceCode) {
		// TODO Auto-generated method stub
		return provinceMapper.selectByPrimaryKey(provinceCode);
	}

}
