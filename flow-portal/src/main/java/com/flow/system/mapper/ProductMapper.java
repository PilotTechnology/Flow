package com.flow.system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.flow.system.model.Order;
import com.flow.system.model.Product;
import com.flow.system.model.Quotation;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByProductCode(String productCode);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);
    
    Product selectByProductCode(String productCode);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
    
    List<Product> listPage(Map<String, Object> map);

	Long getCount(Map<String, Object> map);

	List<Product> findAllProduct();
}