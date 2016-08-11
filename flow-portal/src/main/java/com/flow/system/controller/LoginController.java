package com.flow.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.flow.pub.common.PubLog;
import com.flow.system.bean.UserInfo;
import com.flow.system.model.SysMenu;
import com.flow.system.model.SysRole;
import com.flow.system.model.SysUser;
import com.flow.system.service.LoginService;
import com.flow.system.service.RoleService;
import com.flow.system.service.UserService;

@Controller
@RequestMapping("/portal/system/")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	// 跳转到登录页面
	@RequestMapping("login!selectPage.action")
	public String selectPage(HttpServletRequest request, Model model) {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		
		if (userInfo == null) {
			return "/portal/login.jsp";
		} else {
			return "/portal/index.jsp";
		}
	}

	// 用户登录
	@RequestMapping("login!login.action")
	public String login(SysUser loginUser, String userCode, String password, String randomCode,
			HttpServletRequest request, Model model) {
		if (loginUser != null) {
			loginUser.setPassword(password);
			loginUser.setUserCode(userCode);
		}
		String strMsg = null;
		// 判断验证码是否正确(如果需要校验的话)
		HttpSession session = request.getSession();

		if ((String) session.getAttribute("rand") != null) {
			if (randomCode != null) {
				if (!randomCode.equals((String) session.getAttribute("rand"))) {
					PubLog.error("用户" + userCode + "登录被拒绝，原因：验证码不正确！");
					strMsg = "验证码不正确！";
					model.addAttribute("message", strMsg);
					model.addAttribute("loginUser", loginUser);
					return "/portal/login.jsp";
				}
			}
		}
		try {
			Map<String, String> res = loginService.checkUser(userCode, password);
			String code = res.get("code");
			String msg = res.get("msg");
			if (!"0".equals(code)) {
				model.addAttribute("message", msg);
				model.addAttribute("loginUser", loginUser);
				return "/portal/login.jsp";
			}
			UserInfo userInfo = userService.getUserInfoByUserCode(userCode);
			session.setAttribute("userInfo", userInfo);
			
			//获取菜单权限
			List<SysMenu> menuList = roleService.getMenuListByRoleCode(userInfo.getRoleCode());
			session.setAttribute("menuList", menuList);
			System.out.println("----------->>>>>>" + menuList);
			return "/portal/index.jsp";
		} catch (Exception e) {
			PubLog.error("登录过程出现异常！", e);
			strMsg = "登录过程出现异常！";
			model.addAttribute("message", strMsg);
			model.addAttribute("loginUser", loginUser);
			return "/portal/login.jsp";
		}

	}
}
