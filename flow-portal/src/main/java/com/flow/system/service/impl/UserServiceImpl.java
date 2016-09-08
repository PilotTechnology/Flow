package com.flow.system.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flow.portal.service.AbsPageService;
import com.flow.pub.common.CodeConstants;
import com.flow.pub.common.Constant;
import com.flow.pub.common.PubLog;
import com.flow.pub.util.Encipher;
import com.flow.pub.util.PageUtil;
import com.flow.system.bean.UserInfo;
import com.flow.system.mapper.DistributorMapper;
import com.flow.system.mapper.UserMapper;
import com.flow.system.model.Distributor;
import com.flow.system.model.SysUser;
import com.flow.system.service.UserService;
@Service
public class UserServiceImpl extends AbsPageService<UserInfo> implements UserService{
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private DistributorMapper distributorMapper;
	public SysUser getUserByUserCode(String userCode) {
		return userMapper.getUserByUserCode(userCode);
	}
	
	public UserInfo getUserInfoByUserCode(String userCode) {
		SysUser user = getUserByUserCode(userCode);
		UserInfo userInfo = new UserInfo();
		BeanUtils.copyProperties(user, userInfo);
		Distributor distributor = distributorMapper.selectByUserCode(userInfo.getUserCode());
		if(distributor!=null){
			userInfo.setDistributorCode(distributor.getDistrbutorCode());
		}
		return userInfo;
	}

	@Override
	public PageUtil<UserInfo> listPage(Map<String, Object> map) {
		//分页参数获取
		PageUtil<UserInfo> page = findPage(map);
		Long records = userMapper.getCount(map);
		page.setRecords(records);
		if(records > 0){
			map.put("start", page.getFirstResult());
			map.put("pageSize",page.getPageSize());
			page.setRows(userMapper.listPage(map));
		}
		return page;
	}

	/**
	 * 查询用户是否存在(新增时的判断)
	 */
	public boolean checkExists(UserInfo userInfo) {
		return userMapper.getUserByUserCode(userInfo.getUserCode()) != null;
	}

	/**
	 * 新增用户
	 */
	public void addUser(UserInfo userInfo) {
		try {
			SysUser user = new SysUser();
			BeanUtils.copyProperties(userInfo, user);
			user.setPassword(Encipher.EncodePasswd(Constant.PASSWORD));
			user.setIsEnable(CodeConstants.USER_STATE_ON);
			user.setCreateDate(new Date());
			userMapper.insert(user);
		} catch (BeansException e) {
			PubLog.error("密码初始加密报错",e);
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			PubLog.error("密码初始加密报错",e);
			e.printStackTrace();
		}
	}

	/**
	 * 用户编辑
	 */
	public void update(UserInfo userInfo) {
		SysUser user = new SysUser();
		BeanUtils.copyProperties(userInfo, user);
		userMapper.updateUser(user);
	}

	/**
	 * 删除用户
	 */
	public void deleteUser(String userCode) {
		userMapper.deleteUser(userCode);
	}

	@Override
	public void save(SysUser user) {
		userMapper.insert(user);
	}

	@Override
	public List<UserInfo> findAllUser() {
		return userMapper.findAllUser();
	}

	@Override
	public List<UserInfo> findUserByUserCode(String userCode) {
		return userMapper.findSubUser(userCode);
	}

}
