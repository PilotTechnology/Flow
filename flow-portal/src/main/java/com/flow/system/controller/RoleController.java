package com.flow.system.controller;

import java.util.Date;
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
import com.flow.system.model.SysRole;
import com.flow.system.service.RoleService;

/**
 * 
 * @Description:角色管理
 * 
 */
@Controller
@RequestMapping("/portal/system")
public class RoleController extends BaseController{

	@Autowired
	private RoleService roleService;
	
	/**
	 * 查询角色分页列表
	 * @param request
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "role!selectPage.action")
	public String selectPage(HttpServletRequest request, Model model) throws Exception {
		//转换request参数为map
		Map<String,Object> map = getParameterMap(request);
		
		PageUtil<SysRole> page = roleService.listPage(map);

		model.addAttribute("page",page);
		return "/view/role/roleList.jsp";
	}
	
	/**
	 * 新增角色
	 * @param request
	 * @param Role
	 * @return
	 */
	@RequestMapping(value = "role!addRole.action")
	@ResponseBody
	public Object addRole(HttpServletRequest request, SysRole role){
		try {
			
			if(roleService.checkExists(role)){
				return new BaseResponse(Constant.JSON_FAIL, "角色编号已存在");
			}
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
			role.setCreateUser(userInfo.getUserCode());
			role.setCreateTime(new Date());
			roleService.save(role);
		} catch (Exception e) {
			PubLog.error("新增角色失败 : >> "+role, e);
			return new BaseResponse(Constant.JSON_FAIL, e.getMessage());
		}
		return Constant.successMsg;
	}
	
	/**
	 * 修改角色
	 * @param request
	 * @param Role
	 * @return
	 */
	@RequestMapping(value = "role!editRole.action")
	@ResponseBody
	public Object editRole(HttpServletRequest request, SysRole role){
		try {
			if(roleService.checkExists(role)){
				return new BaseResponse(Constant.JSON_FAIL, "角色编号已存在");
			}
			roleService.update(role);
		} catch (Exception e) {
			PubLog.error("修改角色失败 : >> "+role, e);
			return new BaseResponse(Constant.JSON_FAIL, e.getMessage());
		}
		return Constant.successMsg;
	}
	
	@RequestMapping(value = "role!toEdit.action")
	public SysRole toEdit(HttpServletRequest request,SysRole role){
		return roleService.getRoleByCode(role.getRoleCode());
	}
	
	/**
	 * 删除角色
	 * @param request
	 * @param tSsRolePrivate
	 * @return
	 */
	@RequestMapping(value = "role!delRole.action")
	@ResponseBody
	public Object delRole(HttpServletRequest request,SysRole role) {
		roleService.deleteRole(role);
		return Constant.successMsg;

	}
	
	/**
	 * 获取角色权限列表
	 * @param request
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "role!toGrant.action")
	@ResponseBody
	public SysRole toGrant(HttpServletRequest request,SysRole role){
		return roleService.getRoleMenuByRoleCode(role.getRoleCode());
	}
	
	/**
	 * 角色授权
	 * @param request
	 * @param Role
	 * @return
	 */
	@RequestMapping(value = "role!grant.action")
	@ResponseBody
	public Object grant(HttpServletRequest request){
		UserInfo user = (UserInfo) request.getSession().getAttribute("userInfo");
		String roleCode = request.getParameter("roleCode");
		String menuCodes = request.getParameter("menuCodes");
		
		try {
			roleService.grant(roleCode,menuCodes,user);
		} catch (Exception e) {
			PubLog.error("角色授权失败: >> roleCode :"+roleCode + "; menuCodes ;" + menuCodes, e);
			return new BaseResponse(Constant.JSON_FAIL, e.getMessage());
		}
		return Constant.successMsg;
	}
	
	
	/**
	 * 角色列表
	 * @param request
	 * @param Role
	 * @return
	 */
	@RequestMapping(value = "role!findAll.action")
	@ResponseBody
	public Object findAll(HttpServletRequest request){
		return roleService.findAllRoleList();
	}
	
	
	
	
}
