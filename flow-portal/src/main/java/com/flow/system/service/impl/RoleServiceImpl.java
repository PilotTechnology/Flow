package com.flow.system.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flow.portal.service.AbsPageService;
import com.flow.pub.util.PageUtil;
import com.flow.system.bean.RoleMenu;
import com.flow.system.bean.UserInfo;
import com.flow.system.mapper.MenuMapper;
import com.flow.system.mapper.RoleMapper;
import com.flow.system.model.SysMenu;
import com.flow.system.model.SysRole;
import com.flow.system.service.RoleService;
@Service
public class RoleServiceImpl extends AbsPageService<SysRole> implements RoleService{
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private MenuMapper menuMapper;
	
	@Override
	public PageUtil<SysRole> listPage(Map<String, Object> map) {
		//分页参数获取
		PageUtil<SysRole> page = findPage(map);
		Long records = roleMapper.getCount();
		page.setRecords(records);
		if(records > 0){
			page.setRows(roleMapper.listPage(page.getFirstResult(),page.getPageSize()));
		}
		return page;
	}
	
	public SysRole getRoleByCode(String roleCode) {
		return roleMapper.getRoleByCode(roleCode);
	}

	@Override
	public void save(SysRole role) {
		roleMapper.insert(role);
	}

	public void deleteRole(SysRole role) {
		//TODO 删除角色配置权限
		roleMapper.deleteByPrimaryKey(role.getId());
	}

	@Override
	public void update(SysRole role) {
		roleMapper.updateByPrimaryKey(role);
	}
	
	/**
	 * 判断角色编码是否已存在
	 */
	public boolean checkExists(SysRole role) {
		SysRole oldRole = roleMapper.getRoleByCode(role.getRoleCode());
		if(role.getId()!=null){
			return !(oldRole.getId().equals(role.getId()));
		}else{
			return oldRole != null;
		}
	}

	@Override
	public SysRole getRoleMenuByRoleCode(String roleCode) {
		List<RoleMenu> menuList = menuMapper.getRoleMenuByRoleCode(roleCode); 
		
		SysRole role = roleMapper.getRoleByCode(roleCode);
		role.setMenuList(menuList);
		return role;
	}

	@Override
	public void grant(String roleCode, String menuCodes, UserInfo user) {
		List<String> menuCodeList = Arrays.asList(menuCodes.split(","));
		roleMapper.removeMenu(roleCode);//删除原有授权
		roleMapper.grantMenu(roleCode,menuCodeList,user);//重新授权
	}

	public List<SysRole> findAllRoleList() {
		return roleMapper.listPage(0, 1000); //TODO 默认写死 后续优化
	}

	@Override
	public List<SysMenu> getMenuListByRoleCode(String roleCode) {
		return menuMapper.getSysMenuByRoleCode(roleCode);
	}

}
