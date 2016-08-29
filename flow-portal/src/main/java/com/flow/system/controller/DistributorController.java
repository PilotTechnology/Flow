package com.flow.system.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flow.portal.controller.BaseController;
import com.flow.pub.common.BaseResponse;
import com.flow.pub.common.CodeConstants;
import com.flow.pub.common.Constant;
import com.flow.pub.common.PubLog;
import com.flow.pub.util.MD5Util;
import com.flow.pub.util.PageUtil;
import com.flow.pub.util.StringUtil;
import com.flow.system.bean.UserInfo;
import com.flow.system.model.Distributor;
import com.flow.system.model.SysUser;
import com.flow.system.service.DistributorService;
import com.flow.system.service.UserService;

/**
 * 
 * @Description:分销商管理
 * 
 */
@Controller
@RequestMapping("/portal")
public class DistributorController extends BaseController {
	@Autowired
	private DistributorService distributorService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 分销商分页列表
	 * @param request
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "distributor!selectPage.action")
	public String selectPage(HttpServletRequest request, Distributor distributor, Model model) throws Exception {
		//转换request参数为map
		Map<String,Object> map = getParameterMap(request);
		
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		if (userInfo.getRoleCode().equals(Constant.DISTRIBUTOR_ROLE_CODE)) {
			Distributor loginDistributor = distributorService.getDistributorByCode(userInfo.getUserCode());
			if (loginDistributor != null) {
				map.put("fatherDistributorCode", loginDistributor.getDistrbutorCode());
			}
		}
		if (map.get("fatherDistributorCode") == null) {
			map.put("fatherDistributorCode", "0");
		}
		PageUtil<Distributor> page = distributorService.listPage(map);
		model.addAttribute("page",page);
		if (distributor.getUser() == null) {
			distributor.setUser(new SysUser());
		}
		distributor.getUser().setPhone((String) map.get("phone"));
		model.addAttribute("distributor",distributor);
		model.addAttribute("fatherDistributorCode",map.get("fatherDistributorCode"));
		return "/view/distributor/distributorList.jsp";
	}
	
	/**
	 * 新增分销商
	 * @param request
	 * @param Distributor
	 * @return
	 */
	@RequestMapping(value = "distributor!addDistributor.action")
	@ResponseBody
	public Object addDistributor(HttpServletRequest request, Distributor distributor){
		try {
			if(distributorService.checkExists(distributor)){
				return new BaseResponse(Constant.JSON_FAIL, "分销商已存在");
			}
			SysUser user = distributor.getUser();
			UserInfo loginUserInfo = (UserInfo) request.getSession().getAttribute("userInfo");
			if (loginUserInfo.getRoleCode().equals(Constant.ADMIN_ROLE_CODE)) {
				user.setRoleCode(Constant.DISTRIBUTOR_ROLE_CODE);
				distributor.setFatherDistributorCode("0");
			} else if (loginUserInfo.getRoleCode().equals(Constant.DISTRIBUTOR_ROLE_CODE)) {
				user.setRoleCode(Constant.SON_DISTRIBUTOR_ROLE_CODE);
				Distributor fatherDistributor = distributorService.getDistributorByUserCode(loginUserInfo.getUserCode());
				distributor.setFatherDistributorCode(fatherDistributor.getDistrbutorCode());
			}
			user.setIsEnable(CodeConstants.USER_STATE_ON);
			user.setCreateDate(new Date());
			user.setPassword(MD5Util.EncodeString(user.getPassword()));
			userService.save(distributor.getUser());
			distributor.setAppKey(StringUtil.UUID());
			distributor.setSecretKey(StringUtil.UUID());
			distributorService.save(distributor);
		} catch (Exception e) {
			PubLog.error("新增分销商失败 : >> "+distributor, e);
			return new BaseResponse(Constant.JSON_FAIL, e.getMessage());
		}
		return Constant.successMsg;
	}
	
	@RequestMapping(value = "distributor!toSearch.action")
	public Distributor toSearch(HttpServletRequest request, Distributor distributor){
		return distributorService.getDistributorByCode(distributor.getDistrbutorCode());
	}
	
	@RequestMapping(value = "distributor!toSearchWithInfo.action")
	public Distributor toSearchWithInfo(HttpServletRequest request, String distributorInfo){
		if (distributorInfo == "") {
			return null;
		}
		return distributorService.getDistributorByInfo(distributorInfo);
	}
	
	/**
	 * 修改分销商
	 * @param request
	 * @param Distributor
	 * @return
	 */
	@RequestMapping(value = "distributor!editDistributor.action")
	@ResponseBody
	public Object editProvider(HttpServletRequest request, Distributor distributor){
		try {
			UserInfo userInfo = new UserInfo();
			BeanUtils.copyProperties(distributor.getUser(), userInfo);
			if (userInfo.getPassword() != null && userInfo.getPassword().length() > 0) {
				userInfo.setPassword(MD5Util.EncodeString(userInfo.getPassword()));
			}
			userService.update(userInfo);
			distributorService.update(distributor);;
		} catch (Exception e) {
			PubLog.error("修改分销商失败 : >> "+distributor, e);
			return new BaseResponse(Constant.JSON_FAIL, e.getMessage());
		}
		return Constant.successMsg;
	}
}
