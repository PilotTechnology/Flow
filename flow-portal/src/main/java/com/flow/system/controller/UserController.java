package com.flow.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flow.portal.controller.BaseController;
import com.flow.pub.common.BaseResponse;
import com.flow.pub.common.Constant;
import com.flow.pub.common.PubLog;
import com.flow.pub.util.PageUtil;
import com.flow.system.bean.UserInfo;
import com.flow.system.service.UserService;

/**
 * 
 * @Description:用户管理
 * 
 */
@Controller
@RequestMapping("/portal/system")
public class UserController extends BaseController{

	@Autowired
	private UserService userService;
	
	/**
	 * 查询用户分页列表
	 * @param request
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "user!selectPage.action")
	public String selectPage(HttpServletRequest request, Model model) throws Exception {
		//转换request参数为map
		Map<String,Object> map = getParameterMap(request);
		
		PageUtil<UserInfo> page = userService.listPage(map);

		model.addAttribute("page",page);
		return "/view/user/userList.jsp";
	}
	
	/**
	 * 新的用户列表分页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "user!userList.action")
	@ResponseBody
	public Map<String,Object> userList(HttpServletRequest request)throws Exception {
		List<UserInfo> userList = userService.findAllUser();
		UserInfo user = (UserInfo) request.getSession().getAttribute("userInfo");
		if(Constant.DISTRIBUTOR_ROLE_CODE.equals(user.getRoleCode())){//经销商可以查看二级分销商的报价单
			userList = userService.findUserByUserCode(user.getDistributorCode());
		}
		Map<String,Object> info = new HashMap<>();
		info.put("data", userList);
	    info.put("recordsTotal", String.valueOf(userList.size()));
	    info.put("recordsFiltered", String.valueOf(userList.size()));
	    info.put("draw", "1");
		return info;
	}
	
	/**
	 * 新增用户
	 * @param request
	 * @param Role
	 * @return
	 */
	@RequestMapping(value = "user!addUser.action")
	@ResponseBody
	public Object addUser(HttpServletRequest request, UserInfo userInfo){
		try {
			if(userService.checkExists(userInfo)){
				return new BaseResponse(Constant.JSON_FAIL, "用户编码已存在");
			}
			userService.addUser(userInfo);
		} catch (Exception e) {
			PubLog.error("新增用户失败 : >> "+userInfo, e);
			return new BaseResponse(Constant.JSON_FAIL, e.getMessage());
		}
		return Constant.successMsg;
	}
	
	/**
	 * 修改用户
	 * @param request
	 * @param Role
	 * @return
	 */
	@RequestMapping(value = "user!editUser.action")
	@ResponseBody
	public Object editRole(HttpServletRequest request, UserInfo userInfo){
		try {
			userService.update(userInfo);
		} catch (Exception e) {
			PubLog.error("修改用户失败 : >> "+userInfo, e);
			return new BaseResponse(Constant.JSON_FAIL, e.getMessage());
		}
		return Constant.successMsg;
	}
	
	@RequestMapping(value = "user!toEdit.action")
	@ResponseBody
	public UserInfo toEdit(HttpServletRequest request, String userCode){
		UserInfo user = userService.getUserInfoByUserCode(userCode);
		return user;
	}
	
	/**
	 * 删除用户
	 * @param request
	 * @param tSsRolePrivate
	 * @return
	 */
	@RequestMapping(value = "user!delUser.action")
	@ResponseBody
	public Object delUser(HttpServletRequest request,String userCode) {
		userService.deleteUser(userCode);
		return Constant.successMsg;

	}
	
}
