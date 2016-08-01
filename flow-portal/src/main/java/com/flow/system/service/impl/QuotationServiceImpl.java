package com.flow.system.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flow.portal.service.AbsPageService;
import com.flow.pub.util.PageUtil;
import com.flow.system.mapper.QuotationMapper;
import com.flow.system.model.Quotation;
import com.flow.system.service.QuotationService;
@Service
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

	@Override
	public void save(Quotation quotation) {
		// TODO Auto-generated method stub
		quotationMapper.insert(quotation);
	}

	@Override
	public void delete(Quotation quotation) {
		// TODO Auto-generated method stub
		quotationMapper.deleteByPrimaryKey(quotation.getId());
	}

	@Override
	public void update(Quotation quotation) {
		// TODO Auto-generated method stub
		quotationMapper.updateByPrimaryKeySelective(quotation);
	}

	@Override
	public boolean checkExists(Quotation quotation) {
		// TODO Auto-generated method stub
		Quotation oldQuotation = quotationMapper.selectByQuotationCode(quotation.getServiceCode());
		if(quotation.getServiceCode()!=null){
			return !(oldQuotation.getServiceCode().equals(quotation.getServiceCode()));
		}else{
			return oldQuotation != null;
		}
	}

}
