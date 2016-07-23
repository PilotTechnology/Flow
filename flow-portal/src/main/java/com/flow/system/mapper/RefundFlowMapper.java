package com.flow.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.flow.system.model.RefundFlow;

public interface RefundFlowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RefundFlow record);

    int insertSelective(RefundFlow record);

    RefundFlow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RefundFlow record);

    int updateByPrimaryKey(RefundFlow record);
    
    List<RefundFlow> listPage(@Param("start") Integer start , @Param("pageSize") Integer pageSize);

	Long getCount();
}