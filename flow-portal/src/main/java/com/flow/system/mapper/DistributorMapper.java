package com.flow.system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.flow.system.model.Distributor;

public interface DistributorMapper {
	
	/**
	 * 获取所有的有效下游
	 * @return
	 */
	List<Distributor> getAllEnableList();
	
    int deleteByPrimaryKey(Integer id);
    
    int deleteByDistributorCode(String distributorCode);

    int insert(Distributor record);

    int insertSelective(Distributor record);

    Distributor selectByPrimaryKey(Integer id);
    
    Distributor selectByAppKey(String distributorCode);
    
    Distributor selectByDistributorCode(String distributorCode);
    
    Distributor selectByDistributorInfo(String distributorInfo);
    
    Distributor selectByUserCode(String userCode);
    
    Double selectBanlanceByDistributorCode(String distributorCode);
    
    void addBalance(@Param("distributorCode")String distributorCode, @Param("balance")Double balance);

    void deductBalance(@Param("distributorCode")String distributorCode, @Param("amount")Double amount);
    
    int updateByPrimaryKeySelective(Distributor record);

    int updateByPrimaryKey(Distributor record);
    
    List<Distributor> listPage(Map<String, Object> map);

	Long getCount(Map<String, Object> map);
}