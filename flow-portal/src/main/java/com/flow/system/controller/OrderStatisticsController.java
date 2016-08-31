package com.flow.system.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.flow.portal.controller.BaseController;
import com.flow.pub.common.Constant;
import com.flow.system.bean.UserInfo;
import com.flow.system.model.Distributor;
import com.flow.system.model.OrderStatistics;
import com.flow.system.service.DistributorService;
import com.flow.system.service.OrderStatisticsService;

@Controller
@RequestMapping("/portal")
public class OrderStatisticsController extends BaseController{

	@Autowired
	private OrderStatisticsService orderStatisticsService;
	
	@Autowired
	private DistributorService distributorService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "order!statistics.action")
	public String statistics(HttpServletRequest request, Model model) throws Exception {
		//转换request参数为map
		Map<String,Object> map = getParameterMap(request);
		
		UserInfo loginUserInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		Distributor distributor = distributorService.getDistributorByUserCode(loginUserInfo.getUserCode());
		if (loginUserInfo.getRoleCode().equals(Constant.DISTRIBUTOR_ROLE_CODE)) {
			distributor = distributorService.getDistributorByUserCode(loginUserInfo.getUserCode());
			map.put("distributorCodeScope", distributor.getDistrbutorCode());
		} else if (loginUserInfo.getRoleCode().equals(Constant.SON_DISTRIBUTOR_ROLE_CODE)) {
			map.put("distributorCode", distributor.getDistrbutorCode());
		}
		
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
		
		UserInfo loginUserInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		if (loginUserInfo.getRoleCode().equals(Constant.DISTRIBUTOR_ROLE_CODE)) {
			Distributor distributor = distributorService.getDistributorByUserCode(loginUserInfo.getUserCode());
			map.put("distributorCode", distributor.getDistrbutorCode());
		}
		
		Date currentDate = new Date();
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(currentDate);  
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 30);  
		map.put("beginTime", calendar.getTime());
		map.put("endTime", currentDate);
		
		List<OrderStatistics> list = orderStatisticsService.orderStatisticsBySize(map);
		Long count = orderStatisticsService.getCount(map);
		for (OrderStatistics statistisc : list) {
			Double percent = statistisc.getSuccessNum()/count.doubleValue();
			BigDecimal bigDecimal = new BigDecimal(percent).setScale(2, RoundingMode.UP);
			statistisc.setPercent(bigDecimal.doubleValue());
		}
		
		model.addAttribute("list",list);
		return "/view/order/orderStatistics.jsp";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "order!statisticsbyprovince.action")
	public String statisticsByProvince(HttpServletRequest request, Model model) throws Exception {
		//转换request参数为map
		Map<String,Object> map = getParameterMap(request);
		
		UserInfo loginUserInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		if (loginUserInfo.getRoleCode().equals(Constant.DISTRIBUTOR_ROLE_CODE)) {
			Distributor distributor = distributorService.getDistributorByUserCode(loginUserInfo.getUserCode());
			map.put("distributorCode", distributor.getDistrbutorCode());
		}
		
		Date currentDate = new Date();
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(currentDate);  
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 30);  
		map.put("beginTime", calendar.getTime());
		map.put("endTime", currentDate);
		
		List<OrderStatistics> list = orderStatisticsService.orderStatisticsByProvince(map);
		Long count = orderStatisticsService.getCount(map);
		for (OrderStatistics statistisc : list) {
			Double percent = statistisc.getSuccessNum()/count.doubleValue();
			BigDecimal bigDecimal = new BigDecimal(percent).setScale(2, RoundingMode.UP);
			statistisc.setPercent(bigDecimal.doubleValue());
		}
		
		model.addAttribute("list",list);
		return "/view/order/orderStatistics.jsp";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "order!statisticsbyday.action")
	public String statisticsByDay(HttpServletRequest request, Model model) throws Exception {
		//转换request参数为map
		Map<String,Object> map = getParameterMap(request);
		UserInfo loginUserInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		if (loginUserInfo.getRoleCode().equals(Constant.DISTRIBUTOR_ROLE_CODE)) {
			Distributor distributor = distributorService.getDistributorByUserCode(loginUserInfo.getUserCode());
			map.put("distributorCode", distributor.getDistrbutorCode());
		}
		
		List<OrderStatistics> list = orderStatisticsService.orderStatisticsByDay(map);
		model.addAttribute("list",list);
		return "/view/order/orderStatistics.jsp";
	}
}