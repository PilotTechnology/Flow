package com.flow.system.mapper;

import java.util.List;
import java.util.Map;

import com.flow.system.model.Distributor;

public interface DistributorMapper {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByDistributorCode(String distributorCode);

    int insert(Distributor record);

    int insertSelective(Distributor record);

    Distributor selectByPrimaryKey(Integer id);
    
    Distributor selectByDistributorCode(String distributorCode);

    int updateByPrimaryKeySelective(Distributor record);

    int updateByPrimaryKey(Distributor record);
    
    List<Distributor> listPage(Map map);

	Long getCount(Map map);
}