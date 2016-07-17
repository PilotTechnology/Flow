package com.flow.system.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flow.pub.common.PubLog;
import com.flow.pub.util.Encipher;
import com.flow.system.mapper.UserMapper;
import com.flow.system.model.SysUser;
import com.flow.system.service.LoginService;
@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public Map<String, String> checkUser(String userCode, String rawPwd) {
		Map<String, String> res = new HashMap<String, String>();
		res.put("code", "0");

		boolean safe = isUserCodeSafe(userCode);
		if (!safe) {
			res.put("code", "-1");
			res.put("msg", "用户编码不正确");
			return res;
		}
		SysUser user = userMapper.getUserByUserCode(userCode);
		if (user == null) {
			res.put("code", "-1");
			res.put("msg", "用户不存在");
			return res;
		}
		boolean validPwd = isPwdValid(rawPwd, user.getPassword());
		if (!validPwd) {
			res.put("code", "-1");
			res.put("msg", "密码不正确");
			return res;
		}
		return res;
	}

	public boolean isUserCodeSafe(String userCode) {
		// 判断用户ID是否存在SQL注入
		String strHackerCode = "'<:>:;:(:):%:&:--";
		String strHackerArray[] = strHackerCode.split(":");
		for (int i = 0; i < strHackerArray.length; i++) {
			if (userCode.indexOf(strHackerArray[i]) != -1) {
				PubLog.error("用户" + userCode + "登录被拒绝，原因：用户ID含有系统拒绝的字符！");
				return false;
			}
		}
		return true;
	}

	public boolean isPwdValid(String rawPwd, String encryptPwd) {
		// 加密
		String pwd;
		try {
			pwd = Encipher.EncodePasswd(rawPwd);
		} catch (NoSuchAlgorithmException e) {
			PubLog.error("检查密码失败", e);
			return false;
		}
		if (!encryptPwd.equals(pwd)) {
			return false;
		}
		return true;
	}
}
