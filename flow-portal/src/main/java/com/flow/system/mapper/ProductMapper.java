package com.flow.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.flow.system.model.Product;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByProductCode(String productCode);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);
    
    Product selectByProductCode(String productCode);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
    
    List<Product> listPage(@Param("start") Integer start , @Param("pageSize") Integer pageSize);

	Long getCount();
}