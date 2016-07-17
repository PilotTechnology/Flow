package com.flow.system.mapper;

import java.util.List;

import com.flow.system.bean.RoleMenu;
import com.flow.system.model.SysMenu;

public interface MenuMapper {
	List<RoleMenu> getRoleMenuByRoleCode(String roleCode);
	
    int deleteByPrimaryKey(Integer id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);

}