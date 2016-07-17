package com.flow.system.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flow.system.bean.UserInfo;
import com.flow.system.mapper.UserMapper;
import com.flow.system.model.SysUser;
import com.flow.system.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;
	
	public SysUser getUserByUserCode(String userCode) {
		return userMapper.getUserByUserCode(userCode);
	}
	
	public UserInfo getUserInfoByUserCode(String userCode) {
		SysUser user = getUserByUserCode(userCode);
		UserInfo userInfo = new UserInfo();
		BeanUtils.copyProperties(user, userInfo);
		return userInfo;
	}

}
