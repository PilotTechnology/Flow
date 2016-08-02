package com.flow.system.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flow.portal.service.AbsPageService;
import com.flow.pub.util.PageUtil;
import com.flow.system.mapper.ProviderMapper;
import com.flow.system.model.Provider;
import com.flow.system.service.ProviderService;
@Service
public class ProviderServiceImpl extends AbsPageService<Provider> implements ProviderService {

	@Autowired
	private ProviderMapper providerMapper;
	
	public PageUtil<Provider> listPage(Map<String, Object> map) {
		//分页参数获取
		PageUtil<Provider> page = findPage(map);
		Long records = providerMapper.getCount(map);
		page.setRecords(records);
		if(records > 0){
			map.put("start", page.getFirstResult());
			map.put("pageSize",page.getPageSize());
			page.setRows(providerMapper.listPage(map));
		}
		return page;
	}

	public Provider getProviderByCode(String providerCode) {
		// TODO Auto-generated method stub
		return providerMapper.selectByProviderCode(providerCode);
	}

	public void save(Provider provider) {
		// TODO Auto-generated method stub
		providerMapper.insert(provider);
	}

	public void delete(Provider provider) {
		// TODO Auto-generated method stub
		providerMapper.deleteByProviderCode(provider.getProviderCode());
	}

	public void update(Provider provider) {
		// TODO Auto-generated method stub
		providerMapper.updateByPrimaryKeySelective(provider);
	}

	public boolean checkExists(Provider provider){
		Provider oldProvider = providerMapper.selectByProviderCode(provider.getProviderCode());
		if (oldProvider == null) {
			return false;
		}else {
			return oldProvider.getProviderCode().equals(provider.getProviderCode());
		}
	}
}
