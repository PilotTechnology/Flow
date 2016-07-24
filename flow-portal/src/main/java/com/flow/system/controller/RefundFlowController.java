package com.flow.system.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.flow.portal.controller.BaseController;
import com.flow.pub.util.PageUtil;
import com.flow.system.model.RefundFlow;
import com.flow.system.service.RefundFlowService;

/**
 * 
 * @Description:退款单管理
 * 
 */
@Controller
@RequestMapping("/portal")
public class RefundFlowController extends BaseController {
	@Autowired
	private RefundFlowService refundFlowService;
	
	/**
	 * 退款单分页列表
	 * @param request
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "refundFlow!selectPage.action")
	public String selectPage(HttpServletRequest request, Model model) throws Exception {
		//转换request参数为map
		Map<String,Object> map = getParameterMap(request);
		PageUtil<RefundFlow> page = refundFlowService.listPage(map);
		model.addAttribute("page",page);
		return "/view/refundflow/refundFlowList.jsp";
	}
}
