package com.flow.system.mapper;

import java.util.List;

import com.flow.system.model.SysUser;

public interface UserMapper {
	
	SysUser getUserByUserCode(String userCode);
	
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

	List<SysUser> findPage();
}