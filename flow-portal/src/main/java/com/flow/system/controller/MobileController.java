package com.flow.system.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.flow.portal.controller.BaseController;
import com.flow.pub.util.PageUtil;
import com.flow.system.model.Mobile;
import com.flow.system.service.MobileService;

/**
 * 
 * @Description:手机号段管理
 * 
 */
@Controller
@RequestMapping("/portal")
public class MobileController extends BaseController {
	@Autowired
	private MobileService mobileService;
	
	/**
	 * 查询角色分页列表
	 * @param request
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "mobile!selectPage.action")
	public String selectPage(HttpServletRequest request, Mobile mobile, Model model) throws Exception {
		//转换request参数为map
		Map<String,Object> map = getParameterMap(request);
		PageUtil<Mobile> page = mobileService.listPage(map);
		model.addAttribute("page",page);
		model.addAttribute("mobile",mobile);
		return "/view/mobile/mobileList.jsp";
	}
}
