package com.flow.system.mapper;

import java.util.List;

import com.flow.system.bean.RoleMenu;
import com.flow.system.model.SysMenu;

public interface MenuMapper {
	/**
	 * 用于角色授权
	 * @param roleCode
	 * @return
	 */
	List<RoleMenu> getRoleMenuByRoleCode(String roleCode);
	
	/**
	 * 用于获取用户菜单权限
	 * @param roleCode
	 * @return
	 */
	List<SysMenu> getSysMenuByRoleCode(String roleCode);
	
    int deleteByPrimaryKey(Integer id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);

}