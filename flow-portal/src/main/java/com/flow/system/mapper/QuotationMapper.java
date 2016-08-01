package com.flow.system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.flow.system.model.Quotation;

public interface QuotationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Quotation record);

    int insertSelective(Quotation record);

    Quotation selectByPrimaryKey(Integer id);
    
    Quotation selectByQuotationCode(String quotationCode);

    int updateByPrimaryKeySelective(Quotation record);

    int updateByPrimaryKey(Quotation record);
    
    List<Quotation> listPage(Map map);

	Long getCount(Map map);
	
	List<Quotation> sonListPage(Map map);

	Long getSonCount(Map map);
}