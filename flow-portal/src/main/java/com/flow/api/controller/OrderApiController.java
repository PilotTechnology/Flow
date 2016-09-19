package com.flow.api.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.flow.api.model.OrderRequest;
import com.flow.api.model.OrderResponse;
import com.flow.pub.common.CodeConstants;
import com.flow.pub.common.KeyGenerate;
import com.flow.pub.common.PubLog;
import com.flow.pub.util.MD5Util;
import com.flow.pub.util.MailUtils;
import com.flow.pub.util.PropertisUtil;
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
import com.flow.system.model.ProductForDistributor;
import com.flow.system.model.Quotation;

/**
 * 订单
 * @author MK
 *
 */
@RestController
@RequestMapping(value = "/api")
public class OrderApiController {
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private DistributorMapper distributorMapper;
	
	@Autowired
	private QuotationMapper quotationMapper;
	
	@Autowired
	private ProductForDistributorMapper productForDistributorMapper;
	
	@Autowired
	private MobileMapper mobileMapper;
	
	@Autowired
	private CostFlowMapper costFlowMapper;
	
	/**
	 * 接口1 ： 接收下游订购请求【未测】
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/order.do", method = RequestMethod.GET)
	@ResponseBody
	public OrderResponse get(OrderRequest req, HttpServletRequest request){
		try {
			OrderResponse resp = checkParam(req);
			//step1 :参数非空校验
			if(!StringUtils.isEmpty(resp.getMsg())){//非空校验通过
				PubLog.error("订单请求异常：【参数缺失】 >>" + resp);
				return resp;
			}
			
			//step2:校验账户
			Distributor dist = distributorMapper.selectByAppKey(req.getAppkey());
			if(dist==null){
				resp.setCode(CodeConstants.ACC_ERR);
				resp.setMsg("订单请求异常：【未查找到有效的账户】 ");
				PubLog.error(resp.getMsg() + ">> " + resp);
				return resp;
			}
			
			if(String.valueOf(dist.getState()).equals(CodeConstants.USER_STATE_OFF)){
				resp.setCode(CodeConstants.ACC_ERR_IS_FORBIDDEN);
				resp.setMsg("订单请求异常：【账户被禁用】 ");
				PubLog.error(resp.getMsg() + ">> " + resp);
				return resp;
			}

			if(!dist.getConfiningIp().contains(getIpAddr(request))){
				resp.setCode(CodeConstants.ACC_ERR_IP);
				resp.setMsg("订单请求异常：【ip不在白名单】 ");
				PubLog.error(resp.getMsg() + ">> " + resp);
				return resp;
			}
			
			//step2:校验签名
			String secret = dist.getSecretKey(); 
			String md5Src = String.format("phone=%s&product_id=%s&scope=%s&order_id=%s&time=%s&secret=%s", 
											req.getPhone() , req.getProduct_id(), req.getScope(),
											req.getOrder_id(), req.getTime(), secret);
			if(!MD5Util.EncodeString(md5Src).equalsIgnoreCase(req.getSign())){//签名校验失败
				resp.setCode(CodeConstants.ARG_ERR_SIGN_MISMATCH);
				resp.setMsg("订单请求异常：【签名校验不匹配】 ");
				PubLog.error(resp.getMsg() + ">> " + resp);
				return resp;
			}
			
			if(req.getPhone() == null || req.getPhone().length() < 11){
				resp.setCode(CodeConstants.ARG_ERR_PHONE_FORMAT);
				resp.setMsg("订单请求异常：【手机号格式错误】 ");
				PubLog.error(resp.getMsg() + ">> " + resp);
				return resp;
			}
			
			Mobile mobile = mobileMapper.selectByMobileCode(req.getPhone().substring(0,7));
			if(mobile == null){
				resp.setCode(CodeConstants.ARG_ERR_PHONE_UNFOUND);
				resp.setMsg("订单请求异常：【手机号号段不存在】 ");
				PubLog.error(resp.getMsg() + ">> " + resp);
				return resp;
			}
			
			//step3:判断订单号是否已存在
			int count = orderMapper.getOrderByAppKey(req.getAppkey(),req.getOrder_id());
			if(count > 0){
				resp.setCode(CodeConstants.ARG_ERR_ORDER);
				resp.setMsg("订单请求异常：【订单号重复】 ");
				PubLog.error(resp.getMsg() + ">> " + resp);
				return resp;
			}
			
			Quotation quotation = quotationMapper.getQuotationByDistributorCode(dist.getDistrbutorCode());
			if(quotation == null){
				resp.setCode(CodeConstants.ACC_ERR_NO_QUOTATION);
				resp.setMsg("订单请求异常：【尚未配置报价单】 ");
				PubLog.error(resp.getMsg() + ">> " + resp);
				return resp;
			}
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("serviceCode", quotation.getServiceCode());
			map.put("operatorCode", String.valueOf(mobile.getOperatorCode()));
			map.put("enableArea", req.getScope());
			map.put("size", req.getProduct_id());
			map.put("provinceCode", mobile.getCity().getProvinceCode());

			ProductForDistributor productForDistributor = productForDistributorMapper.getProductByOrder(map);
			
			if(productForDistributor == null) {
				resp.setCode(CodeConstants.ACC_ERR_NO_PRODUCT);
				resp.setMsg("订单请求异常：【流量包尚未配置】 ");
				PubLog.error(resp.getMsg() + ">> " + resp);
				return resp;
			}
			
			// 如果不显示省包，查找给分销商查看的流量包信息
			ProductForDistributor virtualProductForDistributor = null;
			if(quotation.getIsDisplayProvince() == 0) {
				virtualProductForDistributor = productForDistributorMapper.getVirtualProductByOrder(map);
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
			
			if(dist.getBalance() - dist.getFreezing() - virtualProductForDistributor.getPrice()*virtualProductForDistributor.getDiscount()/100 < 0) {
				resp.setCode(CodeConstants.ACC_ERR_NO_BALANCE);
				resp.setMsg("订单请求异常：【余额不足】 ");
				PubLog.error(resp.getMsg() + ">> " + resp);
				return resp;
			}
			
			//step4: 订单信息初始化入库
			Order order = new Order();
			order.setOrderCode(KeyGenerate.getOrderCode());
			order.setDistributorOrderCode(req.getOrder_id());
			order.setOperatorCode(String.valueOf(mobile.getOperatorCode()));
			order.setProviderCode(productForDistributor.getProviderCode());
			order.setDistributorCode(dist.getDistrbutorCode());
			order.setPhone(req.getPhone());
			order.setPhoneProvince(mobile.getCity().getProvince().getProvinceName());
			order.setPhoneCity(mobile.getCity().getCityName());
			order.setProductCode(productForDistributor.getProductCode());
			order.setSize(req.getProduct_id());//流量包大小为流量大小以M为单位，1G为1024
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
			
			distributorMapper.deductBalance(dist.getDistrbutorCode(), order.getPurchased().doubleValue());
			
			CostFlow costFlow = new CostFlow();
			costFlow.setOrderCode(order.getOrderCode());
			costFlow.setDistributorCode(order.getDistributorCode());
			costFlow.setCost(order.getPurchased().doubleValue());
			costFlow.setCurrentBalance(dist.getBalance());
			costFlow.setType(CodeConstants.COST_FLOW_TYPE_KK);
			costFlowMapper.insert(costFlow);
			
			if(dist.getBalance() >= 100 && //余额低于100时 (只发一次邮件)
					(dist.getBalance() - order.getPurchased().doubleValue()) < 100){
				//邮件告警
				sendMail(dist);
			}
			
			OrderResponse successRes = OrderResponse.SUCCESS;
			successRes.setOrder_id(req.getOrder_id());
			successRes.setP_order_id(order.getOrderCode());
			return OrderResponse.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			PubLog.error("系统异常 ：>>" , e);
			return OrderResponse.SYS_ERR;
		}
		
	}

	/**
	 * 校验下游订单请求参数是否缺失
	 * @param req
	 * @return Response
	 */
	private OrderResponse checkParam(OrderRequest req) {
		String msg = "";
		String code = "";
		if(StringUtils.isEmpty(req.getAppkey())){
			msg = "appkey参数缺失,请检查请求！" ;
			code = CodeConstants.ARG_ERR_KEY;
		}else if(StringUtils.isEmpty(req.getOrder_id())){
			msg = "order_id参数缺失,请检查请求！" ;
			code = CodeConstants.ARG_ERR_ORDER;
		}else if(StringUtils.isEmpty(req.getPhone())){
			msg = "phone参数缺失,请检查请求！" ;
			code = CodeConstants.ARG_ERR_PHONE;
		}else if(StringUtils.isEmpty(req.getScope())){
			msg = "scope参数缺失,请检查请求！" ;
			code = CodeConstants.ARG_ERR_SCOPE;
		}else if(StringUtils.isEmpty(req.getProduct_id()+"")){
			msg = "product_id参数缺失,请检查请求！" ;
			code = CodeConstants.ARG_ERR_PRODUCT;
		}else if(StringUtils.isEmpty(req.getTime())){
			msg = "time参数缺失,请检查请求！" ;
			code = CodeConstants.ARG_ERR_TIME;
		}else if(StringUtils.isEmpty(req.getSign())){
			msg = "Sign参数缺失,请检查请求！" ;
			code = CodeConstants.ARG_ERR_SIGN;
		}
		return new OrderResponse(code, msg);
	}
	
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}

		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}

		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		return ip;
	}
	
	/**
	 * 发送告警邮件
	 * @param distributor
	 */
	private void sendMail(Distributor distributor) {
		String smtpServer=PropertisUtil.getValue("smtpServer").trim();
		String userName=PropertisUtil.getValue("userName").trim();
		String passWord=PropertisUtil.getValue("passWord").trim();
		String fromMail=PropertisUtil.getValue("fromMail").trim();
		String mailTo= distributor.getUser().getEmail();
		String mailSubject="【**】流量平台余额告警";
		StringBuilder mailContent=new StringBuilder(distributor.getUser().getNickname()+"， 您好！：");
		mailContent.append("\n");
		mailContent.append("\t\t 您当前的平台账户余额为：" + distributor.getBalance() + "元， 请注意及时充值！");

		try {
			boolean flag=MailUtils.sendMails(smtpServer, userName, passWord, fromMail, mailTo, mailSubject, mailContent.toString(), null);
			if(flag){
				PubLog.info("邮件发送成功!");
			}
		} catch (UnsupportedEncodingException e1) {
			PubLog.error("邮件发送失败!");
			e1.printStackTrace();
		}
	}
}
