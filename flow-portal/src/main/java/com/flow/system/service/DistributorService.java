package com.flow.system.service;

import java.util.List;
import java.util.Map;

import com.flow.pub.util.PageUtil;
import com.flow.system.model.Distributor;

public interface DistributorService {
	PageUtil<Distributor> listPage(Map<String, Object> map);

	Distributor getDistributorByCode(String distributorCode);
	
	Distributor getDistributorByInfo(String distributorInfo);
	
	Distributor getDistributorByUserCode(String userCode);
	
	Double getBalance(String distributorCode);
	
	void addBalance(String distributorCode, Double balance);
	
	void save(Distributor distributor);

	void delete(Distributor distributor);

	void update(Distributor distributor);
	
	boolean checkExists(Distributor distributor);

	List<Distributor> findAllDistributor();
}
