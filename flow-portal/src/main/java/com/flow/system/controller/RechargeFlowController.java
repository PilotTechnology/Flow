package com.flow.system.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.flow.portal.controller.BaseController;
import com.flow.pub.util.PageUtil;
import com.flow.system.model.RechargeFlow;
import com.flow.system.service.RechargeFlowService;

@Controller
@RequestMapping("/portal")
public class RechargeFlowController extends BaseController {
	@Autowired
	private RechargeFlowService rechargeFlowService;
	
	/**
	 * 充值分页列表
	 * @param request
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "rechargeflow!selectPage.action")
	public String selectPage(HttpServletRequest request, RechargeFlow rechargeFlow, Model model) throws Exception {
		//转换request参数为map
		Map<String,Object> map = getParameterMap(request);
		PageUtil<RechargeFlow> page = rechargeFlowService.listPage(map);
		model.addAttribute("page",page);
		model.addAttribute("rechargeFlow",rechargeFlow);
		return "/view/rechargeflow/rechargeFlowList.jsp";
	}
}
