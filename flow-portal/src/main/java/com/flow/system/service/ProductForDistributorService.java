package com.flow.system.service;

import java.util.Map;

import com.flow.pub.util.PageUtil;
import com.flow.system.model.ProductForDistributor;

public interface ProductForDistributorService {
	PageUtil<ProductForDistributor> listPage(Map<String, Object> map);

	ProductForDistributor getProductByCode(Integer id);
	
	void save(ProductForDistributor productForDistributor);

	void delete(ProductForDistributor productForDistributor);

	void update(ProductForDistributor productForDistributor);
	
	boolean checkExists(ProductForDistributor productForDistributor);
}
