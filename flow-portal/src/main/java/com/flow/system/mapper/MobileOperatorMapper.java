package com.flow.system.mapper;

import com.flow.system.model.MobileOperator;

public interface MobileOperatorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MobileOperator record);

    int insertSelective(MobileOperator record);

    MobileOperator selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MobileOperator record);

    int updateByPrimaryKey(MobileOperator record);
}