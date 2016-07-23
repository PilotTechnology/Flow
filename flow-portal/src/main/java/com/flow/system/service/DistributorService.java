package com.flow.system.service;

import java.util.Map;

import com.flow.pub.util.PageUtil;
import com.flow.system.model.Distributor;

public interface DistributorService {
	PageUtil<Distributor> listPage(Map<String, Object> map);

	Distributor getProviderByCode(String distributorCode);
	
	void save(Distributor distributor);

	void delete(Distributor distributor);

	void update(Distributor distributor);
	
	boolean checkExists(Distributor distributor);
}
