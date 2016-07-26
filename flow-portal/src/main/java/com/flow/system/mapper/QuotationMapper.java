package com.flow.system.mapper;

import java.util.List;

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
    
    List<Quotation> listPage(@Param("start") Integer start , @Param("pageSize") Integer pageSize);

	Long getCount();
	
	List<Quotation> sonListPage(@Param("start") Integer start , @Param("pageSize") Integer pageSize);

	Long getSonCount(String fatherQuotationCode);
}