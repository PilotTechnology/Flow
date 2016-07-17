package com.flow.system.service;

import com.flow.system.bean.UserInfo;
import com.flow.system.model.SysUser;

public interface UserService {
	/**
	 * 根据用户编码获取用户实体信息（用户登录校验）
	 * @param userCode
	 * @return
	 */
	SysUser getUserByUserCode(String userCode);
	
	/**
	 * 根据用户编码获取用户session信息（用于session存储）
	 * @param userCode
	 * @return
	 */
	UserInfo getUserInfoByUserCode(String userCode);
}
