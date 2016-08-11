package com.flow.system.model;

import java.util.Date;
import java.util.List;

public class SysMenu {
    private Integer id;

    private String menuCode;

    private String menuName;

    private String upMenu;
    
    private List<SysMenu> subList;

    private String menuPath;

    private Integer sortNo;

    private String isEnable;

    private String createUser;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode == null ? null : menuCode.trim();
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getUpMenu() {
        return upMenu;
    }

    public void setUpMenu(String upMenu) {
        this.upMenu = upMenu == null ? null : upMenu.trim();
    }

    public String getMenuPath() {
        return menuPath;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath == null ? null : menuPath.trim();
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable == null ? null : isEnable.trim();
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

	public List<SysMenu> getSubList() {
		return subList;
	}

	public void setSubList(List<SysMenu> subList) {
		this.subList = subList;
	}

	@Override
	public String toString() {
		return "SysMenu [id=" + id + ", menuCode=" + menuCode + ", menuName=" + menuName + ", upMenu=" + upMenu
				+ ", subList=" + subList + ", menuPath=" + menuPath + "]";
	}
}