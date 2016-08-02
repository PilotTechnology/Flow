package com.flow.system.mapper;

import java.util.List;

import com.flow.system.model.Province;

public interface ProvinceMapper {
	
	/**
	 * 加载省份列表
	 * @return
	 */
	List<Province> findList();
	
    int deleteByPrimaryKey(Integer id);

    int insert(Province record);

    int insertSelective(Province record);

    Province selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Province record);

    int updateByPrimaryKey(Province record);
    
}