package com.flow.system.service;

import java.util.Map;

import com.flow.pub.util.PageUtil;
import com.flow.system.model.Provider;

public interface ProviderService {
	PageUtil<Provider> listPage(Map<String, Object> map);

	Provider getProviderByCode(String providerCode);
	
	void save(Provider provider);

	void delete(Provider provider);

	void update(Provider provider);
	
	boolean checkExists(Provider provider);
}
