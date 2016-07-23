package com.flow.system.service;

import java.util.Map;

import com.flow.pub.util.PageUtil;
import com.flow.system.model.Product;

public interface ProductService {
	PageUtil<Product> listPage(Map<String, Object> map);

	Product getProductByCode(String productCode);
	
	void save(Product product);

	void deleteRole(Product product);

	void update(Product product);
	
	boolean checkExists(Product product);
}
