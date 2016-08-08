package com.flow.system.mapper;

import java.util.List;
import java.util.Map;

import com.flow.system.model.Quotation;

public interface QuotationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Quotation record);

    int insertSelective(Quotation record);

    Quotation selectByPrimaryKey(Integer id);
    
    Quotation selectByQuotationCode(String quotationCode);

    int updateByPrimaryKeySelective(Quotation record);

    int updateByPrimaryKey(Quotation record);
    
    List<Quotation> listPage(Map<String, Object> map);

	Long getCount(Map<String, Object> map);
	
	List<Quotation> sonListPage(Map<String, Object> map);

	Long getSonCount(Map<String, Object> map);
}