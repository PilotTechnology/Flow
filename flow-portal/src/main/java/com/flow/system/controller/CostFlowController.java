package com.flow.system.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.flow.portal.controller.BaseController;
import com.flow.pub.util.PageUtil;
import com.flow.system.model.CostFlow;
import com.flow.system.service.CostFlowService;

/**
 * 
 * @Description:资金流水列表
 * 
 */
@Controller
@RequestMapping("/portal")
public class CostFlowController extends BaseController {
	@Autowired
	private CostFlowService costFlowService;
	
	/**
	 * 资金流水分页列表
	 * @param request
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "costflow!selectPage.action")
	public String selectPage(HttpServletRequest request, Model model) throws Exception {
		//转换request参数为map
		Map<String,Object> map = getParameterMap(request);
		PageUtil<CostFlow> page = costFlowService.listPage(map);
		model.addAttribute("page",page);
		return "/view/costflow/costFlowList.jsp";
	}
}
