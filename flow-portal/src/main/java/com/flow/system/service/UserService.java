package com.flow.system.service;

import java.util.List;
import java.util.Map;

import com.flow.pub.util.PageUtil;
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
	
	/**
	 * 用户列表新的分页方式
	 * @return
	 */
	List<UserInfo> findAllUser();
	/**
	 * 用户列表分页
	 * @param map 查询条件
	 * @return
	 */
	PageUtil<UserInfo> listPage(Map<String, Object> map);
	
	/**
	 * 查询用户是否存在
	 * @param userInfo
	 * @return
	 */
	boolean checkExists(UserInfo userInfo);
	
	/**
	 * 新增用户
	 * @param userInfo
	 */
	void addUser(UserInfo userInfo);
	/**
	 * 编辑用户
	 * @param userInfo
	 */
	void update(UserInfo userInfo);
	/**
	 * 删除用户
	 * @param userCode
	 */
	void deleteUser(String userCode);
	/**
	 * save
	 * @param user
	 */
	void save(SysUser user);

	List<UserInfo> findUserByUserCode(String userCode);

	
}
