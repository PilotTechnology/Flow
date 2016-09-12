package com.flow.system.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flow.api.model.OrderResponse;
import com.flow.portal.controller.BaseController;
import com.flow.pub.common.CodeConstants;
import com.flow.pub.common.Constant;
import com.flow.pub.common.KeyGenerate;
import com.flow.pub.common.PubLog;
import com.flow.pub.util.PageUtil;
import com.flow.system.bean.UserInfo;
import com.flow.system.mapper.CostFlowMapper;
import com.flow.system.mapper.DistributorMapper;
import com.flow.system.mapper.MobileMapper;
import com.flow.system.mapper.OrderMapper;
import com.flow.system.mapper.ProductForDistributorMapper;
import com.flow.system.mapper.QuotationMapper;
import com.flow.system.model.CostFlow;
import com.flow.system.model.Distributor;
import com.flow.system.model.Mobile;
import com.flow.system.model.Order;
import com.flow.system.model.Product;
import com.flow.system.model.ProductForDistributor;
import com.flow.system.model.Quotation;
import com.flow.system.service.DistributorService;
import com.flow.system.service.OrderService;
import com.flow.system.service.ProductService;

@Controller
@RequestMapping("/portal")
public class OrderController extends BaseController{

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private MobileMapper mobileMapper;
	
	@Autowired
	private DistributorService distributorService;
	@Autowired
	private DistributorMapper distributorMapper;

	@Autowired
	private ProductForDistributorMapper productForDistributorMapper;
	@Autowired
	private CostFlowMapper costFlowMapper;
	
	@Autowired
	private QuotationMapper quotationMapper;
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
		
		UserInfo loginUserInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		Distributor distributor = distributorService.getDistributorByUserCode(loginUserInfo.getUserCode());
		if (loginUserInfo.getRoleCode().equals(Constant.DISTRIBUTOR_ROLE_CODE)) {
			distributor = distributorService.getDistributorByUserCode(loginUserInfo.getUserCode());
			map.put("distributorCodeScope", distributor.getDistrbutorCode());
		} else if (loginUserInfo.getRoleCode().equals(Constant.SON_DISTRIBUTOR_ROLE_CODE)) {
			map.put("distributorCode", distributor.getDistrbutorCode());
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
	@Transactional
	@ResponseBody
	public OrderResponse batchInsert(HttpServletRequest request) throws Exception {
		OrderResponse resp = OrderResponse.SYS_ERR;
		//转换request参数为map
		Map<String,Object> map = getParameterMap(request);
		String phone = (String) map.get("phone");
		String productCode = (String) map.get("productCode");
		String distributorCode = (String)map.get("distributorCode");
		if(StringUtils.isEmpty(phone)){
			resp.setCode(CodeConstants.ARG_ERR_PHONE);
			resp.setMsg("手机号为空");
			return resp;
		}
		try {
			Product product = productService.getProductByCode(productCode);
			if(product==null){
				resp.setCode(CodeConstants.ARG_ERR_PRODUCT);
				resp.setMsg("没能查到选择的流量包，请检查流量包是否有误");
				return resp;
			}
			
			Distributor distributor = distributorService.getDistributorByCode(distributorCode);
			if(distributor ==null){
				resp.setMsg("没能查到该分销商，请检查分销商是否存在");
				return resp;
			}
			
			Quotation quotation = quotationMapper.getQuotationByDistributorCode(distributorCode);
			if(quotation == null){
				resp.setCode(CodeConstants.ACC_ERR_NO_QUOTATION);
				resp.setMsg("订单请求异常：【尚未配置报价单】 ");
				PubLog.error(resp.getMsg() + ">> " + resp);
				return resp;
			}
			
			List<String> phoneList = Arrays.asList(phone.split(","));
			if(phoneList!=null && phoneList.size()>0){
				Map<String, Object> paramMap = null;
				for(String phoneTmp : phoneList){
					paramMap = new HashMap<String, Object>();
					Mobile mobile = mobileMapper.selectByMobileCode(phoneTmp.substring(0,7));
					if(mobile == null){
						resp.setCode(CodeConstants.ARG_ERR_PHONE_UNFOUND);
						resp.setMsg("批量充值请求异常：【手机号"+phoneTmp+"号段不存在】 ");
						PubLog.error(resp.getMsg() + ">> " + resp);
						return resp;
					}
					 
					paramMap.put("serviceCode", quotation.getServiceCode());
					paramMap.put("operatorCode", String.valueOf(mobile.getOperatorCode()));
					paramMap.put("enableArea", String.valueOf(quotation.getIsDisplayProvince()));
					paramMap.put("provinceCode", mobile.getCity().getProvinceCode());
					paramMap.put("size", product.getSize());
					ProductForDistributor productForDistributor = productForDistributorMapper.getProductByOrder(paramMap);
					
					if(productForDistributor == null) {
						resp.setCode(CodeConstants.ACC_ERR_NO_PRODUCT);
						resp.setMsg("订单请求异常：【流量包尚未配置】 ");
						PubLog.error(resp.getMsg() + ">> " + resp);
						return resp;
					}
					
					// 如果不显示省包，查找给分销商查看的流量包信息
					ProductForDistributor virtualProductForDistributor = null;
					if(quotation.getIsDisplayProvince() == 0) {
						virtualProductForDistributor = productForDistributorMapper.getVirtualProductByOrder(paramMap);
						if(virtualProductForDistributor == null) {
							virtualProductForDistributor = productForDistributor;
						}else if(virtualProductForDistributor.getPrice()*virtualProductForDistributor.getDiscount() < productForDistributor.getPrice()*productForDistributor.getDiscount()) {
							virtualProductForDistributor = productForDistributor;
						}else {
							virtualProductForDistributor = productForDistributor;
						}
					}else {
						virtualProductForDistributor = productForDistributor;
					}
					
					if(distributor.getBalance() - distributor.getFreezing() - virtualProductForDistributor.getPrice()*virtualProductForDistributor.getDiscount()/100 < 0) {
						resp.setCode(CodeConstants.ACC_ERR_NO_BALANCE);
						resp.setMsg("订单请求异常：【余额不足】 ");
						PubLog.error(resp.getMsg() + ">> " + resp);
						return resp;
					}
					
					//step4: 订单信息初始化入库
					Order order = new Order();
					order.setOrderCode(KeyGenerate.getOrderCode());
					order.setDistributorOrderCode("");
					order.setOperatorCode(String.valueOf(mobile.getOperatorCode()));
					order.setProviderCode(productForDistributor.getProviderCode());
					order.setDistributorCode(distributorCode);
					order.setPhone(phoneTmp);
					order.setPhoneProvince(mobile.getCity().getProvince().getProvinceName());
					order.setPhoneCity(mobile.getCity().getCityName());
					order.setProductCode(product.getProductCode());
					order.setSize(product.getSize());//流量包大小为流量大小以M为单位，1G为1024
					order.setPrice(new BigDecimal(productForDistributor.getPrice()));
					order.setDiscount(new BigDecimal(virtualProductForDistributor.getDiscount()));
					order.setPurchased(new BigDecimal(virtualProductForDistributor.getPrice()*virtualProductForDistributor.getDiscount()/100));
					order.setRealDiscount(new BigDecimal(productForDistributor.getDiscount()));
					order.setRealPurchased(new BigDecimal(productForDistributor.getPrice()*productForDistributor.getDiscount()/100));
					order.setState(CodeConstants.ORDER_STATE_INIT); 
					order.setCreateDate(new Date());
					order.setNoticeState(1);
					order.setErrorTime(0);
					orderMapper.insert(order);
					
					distributorMapper.deductBalance(distributorCode, order.getPurchased().doubleValue());
					
					CostFlow costFlow = new CostFlow();
					costFlow.setOrderCode(order.getOrderCode());
					costFlow.setDistributorCode(order.getDistributorCode());
					costFlow.setCost(order.getPurchased().doubleValue());
					costFlow.setCurrentBalance(distributor.getBalance());
					costFlow.setType(CodeConstants.COST_FLOW_TYPE_KK);
					costFlowMapper.insert(costFlow);
					
					if(distributor.getBalance() >= 100 && //余额低于100时 (只发一次邮件)
							(distributor.getBalance() - order.getPurchased().doubleValue()) < 100){
						//邮件告警 批量充值不需要发邮件了 直接页面提示了
//						OrderApiController.sendMail(distributor);
					}
				}
				
				resp = OrderResponse.SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resp;
	}
}
