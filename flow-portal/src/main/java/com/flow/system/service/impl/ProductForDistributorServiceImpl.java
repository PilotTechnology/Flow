package com.flow.system.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flow.portal.service.AbsPageService;
import com.flow.pub.util.PageUtil;
import com.flow.system.mapper.ProductForDistributorMapper;
import com.flow.system.model.ProductForDistributor;
import com.flow.system.service.ProductForDistributorService;
@Service
public class ProductForDistributorServiceImpl extends AbsPageService<ProductForDistributor> implements ProductForDistributorService {

	@Autowired
	private ProductForDistributorMapper productForDistributorMapper;
	
	@Override
	public PageUtil<ProductForDistributor> listPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		PageUtil<ProductForDistributor> page = findPage(map);
		Long records = productForDistributorMapper.getCountWithQuotationCode((String) map.get("quotationCode"));
		page.setRecords(records);
		if(records > 0){
			page.setRows(productForDistributorMapper.listPage(page.getFirstResult(),page.getPageSize(), (String) map.get("quotationCode")));
		}
		return page;
	}

	@Override
	public ProductForDistributor getProductByCode(Integer id) {
		// TODO Auto-generated method stub
		return productForDistributorMapper.selectByPrimaryKey(id);
	}

	@Override
	public void save(ProductForDistributor productForDistributor) {
		// TODO Auto-generated method stub
		productForDistributorMapper.insert(productForDistributor);
	}

	@Override
	public void delete(ProductForDistributor productForDistributor) {
		// TODO Auto-generated method stub
		productForDistributorMapper.deleteByPrimaryKey(productForDistributor.getId());
	}

	@Override
	public void update(ProductForDistributor productForDistributor) {
		// TODO Auto-generated method stub
		productForDistributorMapper.updateByPrimaryKeySelective(productForDistributor);
	}

	@Override
	public boolean checkExists(ProductForDistributor productForDistributor) {
		// TODO Auto-generated method stub
		ProductForDistributor oldProductForDistributor = productForDistributorMapper.selectByPrimaryKey(productForDistributor.getId());
		if(productForDistributor.getProductCode()!=null){
			return !(oldProductForDistributor.getProductCode().equals(productForDistributor.getProductCode()));
		}else{
			return oldProductForDistributor != null;
		}
	}

}
