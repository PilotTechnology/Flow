package com.flow.system.mapper;

import java.util.List;
import java.util.Map;

import com.flow.system.model.RechargeFlow;

public interface RechargeFlowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RechargeFlow record);

    int insertSelective(RechargeFlow record);

    RechargeFlow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RechargeFlow record);

    int updateByPrimaryKey(RechargeFlow record);
    
    List<RechargeFlow> listPage(Map<String, Object> map);

	Long getCount(Map<String, Object> map);
}