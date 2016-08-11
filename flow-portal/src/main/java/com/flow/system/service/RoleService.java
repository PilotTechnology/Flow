package com.flow.system.service;

import java.util.List;
import java.util.Map;

import com.flow.pub.util.PageUtil;
import com.flow.system.bean.UserInfo;
import com.flow.system.model.SysMenu;
import com.flow.system.model.SysRole;

public interface RoleService {

	PageUtil<SysRole> listPage(Map<String, Object> map);

	List<SysRole> findAllRoleList();
	
	List<SysMenu> getMenuListByRoleCode(String roleCode);
	
	SysRole getRoleByCode(String roleCode);

	SysRole getRoleMenuByRoleCode(String roleCode);
	
	void save(SysRole role);

	void deleteRole(SysRole role);

	void update(SysRole role);

	boolean checkExists(SysRole role);

	void grant(String roleCode, String menuCodes, UserInfo user);


}
