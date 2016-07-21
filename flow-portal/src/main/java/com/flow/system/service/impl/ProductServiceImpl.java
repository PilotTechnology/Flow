package com.flow.system.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flow.portal.service.AbsPageService;
import com.flow.pub.util.PageUtil;
import com.flow.system.mapper.ProductMapper;
import com.flow.system.model.Product;
import com.flow.system.service.ProductService;
@Service
public class ProductServiceImpl extends AbsPageService<Product> implements ProductService {

	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public PageUtil<Product> listPage(Map<String, Object> map) {
		//分页参数获取
		PageUtil<Product> page = findPage(map);
		Long records = productMapper.getCount();
		page.setRecords(records);
		if(records > 0){
			page.setRows(productMapper.listPage(page.getFirstResult(),page.getPageSize()));
		}
		return page;
	}

	@Override
	public Product getProductByCode(String productCode) {
		// TODO Auto-generated method stub
		return productMapper.selectByProductCode(productCode);
	}

	@Override
	public void save(Product product) {
		// TODO Auto-generated method stub
		productMapper.insert(product);
	}

	@Override
	public void deleteRole(Product product) {
		// TODO Auto-generated method stub
		productMapper.deleteByPrimaryKey(product.getId());
	}

	@Override
	public void update(Product product) {
		// TODO Auto-generated method stub
		productMapper.updateByPrimaryKey(product);
	}

	@Override
	public boolean checkExists(Product product) {
		Product oldProduct = productMapper.selectByProductCode(product.getProductCode());
		if(product.getId()!=null){
			return !(oldProduct.getProductCode().equals(product.getProductCode()));
		}else{
			return oldProduct != null;
		}
	}

}
