package com.flow.system.bean;

public class RoleMenu {
	private Integer id;
	
	private Integer pId;
	
    private String menuCode;

    private String menuName;
    
    private int isGrant; //是否已授权  0 : 未授权  1：已授权

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public void setIsGrant(int isGrant) {
		this.isGrant = isGrant;
	}

	public int getIsGrant() {
		return isGrant;
	}

}
