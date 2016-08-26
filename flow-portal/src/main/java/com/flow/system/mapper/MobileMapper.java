package com.flow.system.mapper;

import java.util.List;
import java.util.Map;

import com.flow.system.model.Mobile;

public interface MobileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Mobile record);

    int insertSelective(Mobile record);

    Mobile selectByMobileCode(String mobileCode);

    int updateByPrimaryKeySelective(Mobile record);

    int updateByPrimaryKey(Mobile record);
    
    List<Mobile> listPage(Map<String, Object> map);

	Long getCount(Map<String, Object> map);
}