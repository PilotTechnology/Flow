package com.flow.system.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flow.api.model.OrderResponse;
import com.flow.portal.controller.BaseController;
import com.flow.pub.common.CodeConstants;
import com.flow.pub.common.Constant;
import com.flow.pub.util.PageUtil;
import com.flow.pub.util.StringUtil;
import com.flow.system.model.Distributor;
import com.flow.system.model.Order;
import com.flow.system.model.Product;
import com.flow.system.service.DistributorService;
import com.flow.system.service.OrderService;
import com.flow.system.service.ProductService;

@Controller
@RequestMapping("/portal")
public class OrderController extends BaseController{

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private DistributorService distributorService;
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
		PageUtil<Order> page = orderService.listPage(map);
		
		model.addAttribute("page",page);
		//查询条件页面回显
		model.addAttribute("order", queryBean);
		
		return "/view/order/orderList.jsp";
	}
	
	/**
	 * 查询订单分页列表
	 * @param request
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "order!batchInit.action")
	public String batchInit(HttpServletRequest request,Model model) throws Exception {
		//获取所有的流量包
		List<Product> productList = productService.findAllProduct();
		model.addAttribute("productList",productList);
		
		List<Distributor> distributorList = distributorService.findAllDistributor();
		model.addAttribute("distributorList" , distributorList);
		return "/view/order/batchInit.jsp";
	}
	
	/**
	 * 批量充值
	 * @param request
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "order!batchInsert.action")
	@ResponseBody
	public String batchInsert(HttpServletRequest request) throws Exception {
		//转换request参数为map
		Map<String,Object> map = getParameterMap(request);
		String phone = (String) map.get("phone");
		String productCode = (String) map.get("productCode");
		String distributorCode = (String)map.get("distributorCode");
		if(StringUtils.isEmpty(phone)){
			return "手机号为空";
		}
		Product product = productService.getProductByCode(productCode);
		if(product==null){
			return "没能查到选择的流量包，请检查流量包是否有误";
		}
		
		Distributor distributor = distributorService.getDistributorByCode(distributorCode);
		if(distributor ==null){
			return "没能查到该分销商，请检查分销商是否存在";
		}
		//TODO 订购
		
		return Constant.JSON_SUCCESS;
	}
}
