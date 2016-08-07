package com.flow.system.controller;

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
		PageUtil<Distributor> page = distributorService.listPage(map);
		model.addAttribute("page",page);
		if (distributor.getUser() == null) {
			distributor.setUser(new SysUser());
		}
		distributor.getUser().setPhone((String) map.get("phone"));
		model.addAttribute("distributor",distributor);
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
			userService.save(distributor.getUser());
			distributorService.save(distributor);
		} catch (Exception e) {
			PubLog.error("新增分销商失败 : >> "+distributor, e);
			return new BaseResponse(Constant.JSON_FAIL, e.getMessage());
		}
		return Constant.successMsg;
	}
	
	@RequestMapping(value = "distributor!toSearch.action")
	public Distributor toSearch(HttpServletRequest request, Distributor distributor){
		System.out.println(distributor);
		return distributorService.getDistributorByCode(distributor.getDistrbutorCode());
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
			userService.save(distributor.getUser());
			distributorService.update(distributor);;
		} catch (Exception e) {
			PubLog.error("修改分销商失败 : >> "+distributor, e);
			return new BaseResponse(Constant.JSON_FAIL, e.getMessage());
		}
		return Constant.successMsg;
	}
}
