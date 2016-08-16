package com.flow.system.service;

import java.util.List;
import java.util.Map;

import com.flow.pub.util.PageUtil;
import com.flow.system.model.Product;

public interface ProductService {
	PageUtil<Product> listPage(Map<String, Object> map);

	Product getProductByCode(String productCode);
	
	Product getProductByPrimaryKey(Integer id);
	
	void save(Product product);

	void delete(Product product);

	void update(Product product);
	
	boolean checkExists(Product product);

	List<Product> findAllProduct();
}
