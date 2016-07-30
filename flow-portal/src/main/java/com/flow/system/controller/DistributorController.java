package com.flow.system.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.flow.portal.controller.BaseController;
import com.flow.pub.util.PageUtil;
import com.flow.system.model.Distributor;
import com.flow.system.model.SysUser;
import com.flow.system.service.DistributorService;

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
}
