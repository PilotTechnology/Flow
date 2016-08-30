package com.flow.system.service;

import java.util.List;
import java.util.Map;

import com.flow.pub.util.PageUtil;
import com.flow.system.model.Quotation;

public interface QuotationService {
	PageUtil<Quotation> listPage(Map<String, Object> map);
	
	PageUtil<Quotation> sonListPage(Map<String, Object> map);

	Quotation getQuotationByCode(String quotationCode);
	
	void save(Quotation quotation,String products);

	void delete(Quotation quotation);

	void update(String products , Quotation quotation);
	
	boolean checkExists(Quotation quotation);

	List<Map<String, Object>> findProductsByServiceCode(String id);
}
