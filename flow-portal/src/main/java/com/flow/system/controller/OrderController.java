package com.flow.system.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.flow.portal.controller.BaseController;
import com.flow.pub.util.PageUtil;
import com.flow.system.model.Order;
import com.flow.system.service.OrderService;

@Controller
@RequestMapping("/portal")
public class OrderController extends BaseController{

	@Autowired
	private OrderService orderService;
	
	/**
	 * 查询订单分页列表
	 * @param request
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "order!selectPage.action")
	public String selectPage(HttpServletRequest request,Order queryBean , Model model) throws Exception {
		//转换request参数为map
		Map<String,Object> map = getParameterMap(request);
		
		if (map.get("searchType") != null && !map.get("searchType").equals("-1")) {
			map.put("state", "0");
		}
		
		PageUtil<Order> page = orderService.listPage(map);
		
		model.addAttribute("page",page);
		//查询条件页面回显
		model.addAttribute("order", queryBean);
		
		return "/view/order/orderList.jsp";
	}
}
