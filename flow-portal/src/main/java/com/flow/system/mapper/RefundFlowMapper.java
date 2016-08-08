package com.flow.system.mapper;

import java.util.List;
import java.util.Map;

import com.flow.system.model.RefundFlow;

public interface RefundFlowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RefundFlow record);

    int insertSelective(RefundFlow record);

    RefundFlow selectByPrimaryKey(Integer id);
    
    RefundFlow selectByOrderCode(String orderCode);

    int updateByPrimaryKeySelective(RefundFlow record);

    int updateByPrimaryKey(RefundFlow record);
    
    List<RefundFlow> listPage(Map<String, Object> map);

	Long getCount(Map<String, Object> map);
}