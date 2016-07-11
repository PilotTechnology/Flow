package com.flow.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flow.mapper.ProviderMapper;
import com.flow.model.Provider;
import com.flow.service.ProviderService;

@Service
public class ProviderServiceImpl implements ProviderService {
	@Autowired
	private ProviderMapper mapper;

	@Override
	public int insert(Provider record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(Provider record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public Provider findById(int id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Provider> findList() {
		// TODO Auto-generated method stub
		return mapper.findPage();
	}
	
}
