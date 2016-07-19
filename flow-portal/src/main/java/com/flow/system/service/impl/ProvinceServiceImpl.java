package com.flow.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flow.system.mapper.ProvinceMapper;
import com.flow.system.model.Province;
import com.flow.system.service.ProvinceService;
@Service
public class ProvinceServiceImpl implements ProvinceService {

	@Autowired
	private ProvinceMapper provinceMapper;
	
	@Override
	public Province getProvinceByCode(Integer provinceCode) {
		return provinceMapper.selectByPrimaryKey(provinceCode);
	}

}
