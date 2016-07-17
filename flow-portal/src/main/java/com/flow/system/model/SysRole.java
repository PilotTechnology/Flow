package com.flow.system.model;

import java.util.Date;
import java.util.List;

import com.flow.system.bean.RoleMenu;

public class SysRole {
    private Integer id;

    private String roleCode;

    private String roleName;

    private String createUser;

    private Date createTime;
    
    private List<RoleMenu> menuList;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public List<RoleMenu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<RoleMenu> menuList) {
		this.menuList = menuList;
	}

	@Override
	public String toString() {
		return "SysRole [roleCode=" + roleCode + ", roleName=" + roleName + ", createUser=" + createUser + "]";
	}
}