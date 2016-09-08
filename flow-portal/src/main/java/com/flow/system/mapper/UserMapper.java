package com.flow.system.mapper;

import java.util.List;
import java.util.Map;

import com.flow.system.bean.UserInfo;
import com.flow.system.model.SysUser;

public interface UserMapper {
	
	/**
	 * 获取系统用户用于登录
	 * @param userCode
	 * @return
	 */
	SysUser getUserByUserCode(String userCode);
	
	/**
	 * 获取用户总数
	 * @return
	 */
	Long getCount(Map<String,Object> map);
	
	/**
	 * 获取用户条件列表
	 * @param map
	 * @return
	 */
	List<UserInfo> listPage(Map<String,Object> map);

	/**
	 * 更新用户
	 * @param record
	 * @return
	 */
	int updateUser(SysUser user);
	
	/**
	 * 删除用户
	 * @param userCode
	 * @return
	 */
    int deleteUser(String userCode);
    
    /**
     * 添加用户
     * @param record
     * @return
     */
    int insert(SysUser record);

	List<SysUser> findPage();

	List<UserInfo> findAllUser();

	List<UserInfo> findSubUser(String userCode);

}