package com.flow.system.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (map.get("searchType").equals("0")) {
				calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 24);
				map.put("beginTime", df.format(calendar.getTime()));
				calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + 12);
				map.put("endTime", df.format(calendar.getTime()));
			} else if (map.get("searchType").equals("1")) {
				calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 36);
				map.put("beginTime", df.format(calendar.getTime()));
				calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + 12);
				map.put("endTime", df.format(calendar.getTime()));
			} else if (map.get("searchType").equals("2")) {
				calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 48);
				map.put("beginTime", df.format(calendar.getTime()));
				calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + 12);
				map.put("endTime", df.format(calendar.getTime()));
			} else if (map.get("searchType").equals("3")) {
				calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 48);
				map.put("endTime", df.format(calendar.getTime()));
			}
		}
		System.out.println(map);
		PageUtil<Order> page = orderService.listPage(map);
		
		model.addAttribute("page",page);
		//查询条件页面回显
		model.addAttribute("order", queryBean);
		
		return "/view/order/orderList.jsp";
	}
}
