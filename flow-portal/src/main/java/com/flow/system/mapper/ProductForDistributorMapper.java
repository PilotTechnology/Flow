package com.flow.system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.flow.system.model.ProductForDistributor;

public interface ProductForDistributorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductForDistributor record);

    int insertSelective(ProductForDistributor record);

    ProductForDistributor selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductForDistributor record);

    int updateByPrimaryKey(ProductForDistributor record);
    
    List<ProductForDistributor> listPage(@Param("start") Integer start , @Param("pageSize") Integer pageSize, @Param("quotationCode") String quotationCode);

    List<ProductForDistributor> getProducts(Map<String, String> map);
    
    Long getCountWithQuotationCode(String quotationCode);
	
	ProductForDistributor getProductByOrder(Map<String, Object> map);
	
	ProductForDistributor getVirtualProductByOrder(Map<String, Object> map);
}