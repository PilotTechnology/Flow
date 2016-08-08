package com.flow.system.mapper;

import java.util.List;
import java.util.Map;

import com.flow.system.model.CostFlow;

public interface CostFlowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CostFlow record);

    int insertSelective(CostFlow record);

    CostFlow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CostFlow record);

    int updateByPrimaryKey(CostFlow record);
    
    List<CostFlow> listPage(Map<String, Object> map);

	Long getCount(Map<String, Object> map);
}