package com.flow.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
    
    List<Distributor> listPage(@Param("start") Integer start , @Param("pageSize") Integer pageSize);

	Long getCount();
}