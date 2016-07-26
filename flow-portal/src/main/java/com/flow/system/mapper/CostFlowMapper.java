package com.flow.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.flow.system.model.CostFlow;

public interface CostFlowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CostFlow record);

    int insertSelective(CostFlow record);

    CostFlow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CostFlow record);

    int updateByPrimaryKey(CostFlow record);
    
    List<CostFlow> listPage(@Param("start") Integer start , @Param("pageSize") Integer pageSize);

	Long getCount();
}