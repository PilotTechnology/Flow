package com.flow.system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.flow.system.model.Mobile;

public interface MobileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Mobile record);

    int insertSelective(Mobile record);

    Mobile selectByMobileCode(String mobileCode);

    int updateByPrimaryKeySelective(Mobile record);

    int updateByPrimaryKey(Mobile record);
    
    List<Mobile> listPage(@Param("start") Integer start , @Param("pageSize") Integer pageSize);

	Long getCount();
	
	/**
	 * 获取电话归属地
	 * @return
	 */
	String getMobile(String mobileCode);
}