package com.flow.system.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.flow.portal.controller.BaseController;
import com.flow.system.model.OrderStatistics;
import com.flow.system.service.OrderStatisticsService;

@Controller
@RequestMapping("/portal")
public class OrderStatisticsController extends BaseController{

	@Autowired
	private OrderStatisticsService orderStatisticsService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "order!statistics.action")
	public String statistics(HttpServletRequest request, Model model) throws Exception {
		//转换request参数为map
		Map<String,Object> map = getParameterMap(request);
		
		List<OrderStatistics> list = orderStatisticsService.orderStatisticsByDistributor(map);
		
		model.addAttribute("list",list);
		model.addAttribute("distributorCode",map.get("distributorCode"));
		model.addAttribute("endTime",map.get("endTime"));
		model.addAttribute("beginTime",map.get("beginTime"));
		return "/view/order/orderStatistics.jsp";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "order!statisticsbysize.action")
	public String statisticsBySize(HttpServletRequest request, Model model) throws Exception {
		//转换request参数为map
		Map<String,Object> map = getParameterMap(request);
		List<OrderStatistics> list = orderStatisticsService.orderStatisticsBySize(map);
		model.addAttribute("list",list);
		return "/view/order/orderStatistics.jsp";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "order!statisticsbyprovince.action")
	public String statisticsByProvince(HttpServletRequest request, Model model) throws Exception {
		//转换request参数为map
		Map<String,Object> map = getParameterMap(request);
		List<OrderStatistics> list = orderStatisticsService.orderStatisticsBySize(map);
		model.addAttribute("list",list);
		return "/view/order/orderStatistics.jsp";
	}
}