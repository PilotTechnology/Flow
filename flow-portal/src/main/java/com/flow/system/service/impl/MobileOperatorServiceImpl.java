package com.flow.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flow.system.mapper.MobileOperatorMapper;
import com.flow.system.model.MobileOperator;
import com.flow.system.service.MobileOperatorService;
@Service
public class MobileOperatorServiceImpl implements MobileOperatorService {

	@Autowired
	private MobileOperatorMapper mobileOperatorMapper;
	
	@Override
	public MobileOperator getMobileOperatorByCode(Integer mobileOperatorCode) {
		// TODO Auto-generated method stub
		return mobileOperatorMapper.selectByPrimaryKey(mobileOperatorCode);
	}

}
