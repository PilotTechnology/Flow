package com.flow.system.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.flow.pub.common.Constant;
import com.flow.pub.common.PubLog;
import com.flow.system.bean.UserInfo;
import com.flow.system.model.Distributor;
import com.flow.system.model.SysMenu;
import com.flow.system.model.SysUser;
import com.flow.system.service.DistributorService;
import com.flow.system.service.LoginService;
import com.flow.system.service.OrderStatisticsService;
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
	
	@Autowired
	private DistributorService distributorService;
	
	@Autowired
	private OrderStatisticsService orderStatisticsService;
	
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
	
	// 用户退出
	@RequestMapping("login!logout.action")
	public String logout(HttpServletRequest request, Model model) {
		request.getSession().invalidate();
		return "redirect:/portal/login.jsp";
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
			
			String distributorCode = "";
			if (userInfo.getRoleCode().equals(Constant.DISTRIBUTOR_ROLE_CODE) || userInfo.getRoleCode().equals(Constant.SON_DISTRIBUTOR_ROLE_CODE)) {
				Distributor distributor = distributorService.getDistributorByUserCode(userInfo.getUserCode());
				distributorCode = distributor.getDistrbutorCode();
				Double balance = distributorService.getBalance(distributorCode);
				model.addAttribute("balance", balance);
			} else {
				model.addAttribute("balance", 0);
			}
			
			Map<String, Object> map = new HashMap<String, Object>();
			Calendar todayStart = Calendar.getInstance();  
	        todayStart.set(Calendar.HOUR_OF_DAY, 0);  
	        todayStart.set(Calendar.MINUTE, 0);  
	        todayStart.set(Calendar.SECOND, 0);
	        map.put("beginTime", todayStart.getTime());
	        
	        Calendar todayEnd = Calendar.getInstance();  
	        todayEnd.set(Calendar.HOUR_OF_DAY, 23);  
	        todayEnd.set(Calendar.MINUTE, 59);  
	        todayEnd.set(Calendar.SECOND, 59);
	        map.put("endTime", todayEnd.getTime());
			
	        map.put("distributorCode", distributorCode);
	        
			Long orderCount = orderStatisticsService.getCountOfToday(map);
			model.addAttribute("orderCount", orderCount);
			
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
