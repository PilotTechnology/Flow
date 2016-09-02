package com.flow.system.service.impl;

import java.util.List;
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
		Long records = productMapper.getCount(map);
		page.setRecords(records);
		if(records > 0){
			map.put("start", page.getFirstResult());
			map.put("pageSize",page.getPageSize());
			page.setRows(productMapper.listPage(map));
		}
		return page;
	}

	@Override
	public Product getProductByCode(String productCode) {
		// TODO Auto-generated method stub
		return productMapper.selectByProductCode(productCode);
	}
	
	@Override
	public Product getProductByPrimaryKey(Integer id) {
		return productMapper.selectByPrimaryKey(id);
	}

	@Override
	public void save(Product product) {
		// TODO Auto-generated method stub
		productMapper.insert(product);
	}

	@Override
	public void delete(Product product) {
		// TODO Auto-generated method stub
		productMapper.deleteByProductCode(product.getProductCode());
	}

	@Override
	public void update(Product product) {
		// TODO Auto-generated method stub
		productMapper.updateByPrimaryKeySelective(product);
	}

	@Override
	public boolean checkExists(Product product) {
		Product oldProduct = productMapper.selectByProductCode(product.getProductCode());
		if (oldProduct != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Product> findAllProduct() {
		return productMapper.findAllProduct();
	}

	@Override
	public List<Product> findProductByUserCode(String userCode) {
		return productMapper.findProductByUserCode(userCode);
	}

}
