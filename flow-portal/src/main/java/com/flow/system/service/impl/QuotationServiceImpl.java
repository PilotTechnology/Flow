package com.flow.system.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.flow.portal.service.AbsPageService;
import com.flow.pub.util.PageUtil;
import com.flow.system.mapper.QuotationMapper;
import com.flow.system.model.Product;
import com.flow.system.model.Quotation;
import com.flow.system.service.QuotationService;
@Service
@Transactional
public class QuotationServiceImpl extends AbsPageService<Quotation> implements QuotationService {

	@Autowired
	private QuotationMapper quotationMapper;
	
	@Override
	public PageUtil<Quotation> listPage(Map<String, Object> map) {
		//分页参数获取
		PageUtil<Quotation> page = findPage(map);
		Long records = quotationMapper.getCount(map);
		page.setRecords(records);
		if(records > 0){
			map.put("start", page.getFirstResult());
			map.put("pageSize",page.getPageSize());
			page.setRows(quotationMapper.listPage(map));
		}
		return page;
	}

	@Override
	public PageUtil<Quotation> sonListPage(Map<String, Object> map) {
		//分页参数获取
		PageUtil<Quotation> page = findPage(map);
		Long records = quotationMapper.getCount(map);
		page.setRecords(records);
		if(records > 0){
			map.put("start", page.getFirstResult());
			map.put("pageSize",page.getPageSize());
			page.setRows(quotationMapper.sonListPage(map));
		}
		return page;
	}

	@Override
	public Quotation getQuotationByCode(String quotationCode) {
		// TODO Auto-generated method stub
		return quotationMapper.selectByQuotationCode(quotationCode);
	}

	/**
	 * 生成报价单
	 */
	public void save(Quotation quotation ,String products) {
		quotationMapper.insert(quotation);//添加报价单
		//组装products
		List<Product> list = new ArrayList<>();
		if(!StringUtils.isEmpty(products)){
			Product pro = null;
		   for(String tmp : Arrays.asList(products.split(","))){
			   pro = new Product();
			   String[] arr = tmp.split("_");
			   pro.setProductCode(arr[0]);
			   pro.setDiscount(Double.valueOf(arr[1]));
			   pro.setState(1);//TODO 默认激活
  			   list.add(pro);
		   }
		   quotationMapper.insertServProd(quotation.getServiceCode() , list);
		}
	}

	@Override
	public void delete(Quotation quotation) {
		quotationMapper.deleteByPrimaryKey(quotation.getId());
	}

	public void update(String products , Quotation quotation) {
		quotationMapper.updateByPrimaryKeySelective(quotation);
		
		//组装products
		List<Product> list = new ArrayList<>();
		if(!StringUtils.isEmpty(products)){
			Product pro = null;
		   for(String tmp : Arrays.asList(products.split(","))){
			   pro = new Product();
			   String[] arr = tmp.split("_");
			   pro.setProductCode(arr[0]);
			   pro.setDiscount(Double.valueOf(arr[1]));
			   pro.setState(1);//TODO 默认激活
  			   list.add(pro);
		   }
		   quotationMapper.deleteServProd(quotation.getServiceCode());
		   quotationMapper.insertServProd(quotation.getServiceCode() , list);
		}
	}

	@Override
	public boolean checkExists(Quotation quotation) {
		Quotation oldQuotation = quotationMapper.selectByQuotationCode(quotation.getServiceCode());
		if(quotation.getServiceCode()!=null){
			return !(oldQuotation.getServiceCode().equals(quotation.getServiceCode()));
		}else{
			return oldQuotation != null;
		}
	}

	@Override
	public List<Map<String, Object>> findProductsByServiceCode(String id) {
		return quotationMapper.findProductsByServiceCode(id);
	}

}
