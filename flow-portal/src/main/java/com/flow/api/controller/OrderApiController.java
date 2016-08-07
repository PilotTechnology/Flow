package com.flow.api.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.flow.api.model.CallBackRequest;
import com.flow.api.model.OrderRequest;
import com.flow.api.model.OrderResponse;
import com.flow.pub.common.CodeConstants;
import com.flow.pub.common.KeyGenerate;
import com.flow.pub.common.PubLog;
import com.flow.pub.util.MD5Util;
import com.flow.system.mapper.DistributorMapper;
import com.flow.system.mapper.OrderMapper;
import com.flow.system.model.Distributor;
import com.flow.system.model.Order;

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
	/**
	 * 接口1 ： 接收下游订购请求【未测】
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/order.do", method = RequestMethod.GET)
	@ResponseBody
	public OrderResponse get(OrderRequest req){
		try {
			OrderResponse resp = checkParam(req);
			//step1 :参数非空校验
			if(!StringUtils.isEmpty(resp.getMsg())){//非空校验通过
				PubLog.error("订单请求异常：【参数缺失】 >>" + resp);
				return resp;
			}
			
			//step2:校验签名
			Distributor dist = distributorMapper.selectByAppKey(req.getAppkey());
			if(dist==null){
				resp.setCode(CodeConstants.ACC_ERR);
				resp.setMsg("订单请求异常：【未查找到有效的下游账户】 ");
				PubLog.error(resp.getMsg() + ">> " + resp);
				return resp;
			}
			
			String secret = dist.getSecretKey(); 
			String md5Src = String.format("phone=%s&product_id=%s&order_id=%s&time=%s&secret=%s", 
											req.getPhone() , req.getProduct_id(), 
											req.getOrder_id(), req.getTime(), secret);
			if(!MD5Util.EncodeString(md5Src).equalsIgnoreCase(req.getSign())){//签名校验失败
				resp.setCode(CodeConstants.ARG_ERR_SIGN);
				resp.setMsg("订单请求异常：【签名校验失败】 ");
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
			//step4: 订单信息初始化入库
			Order order = new Order();
			order.setOrderCode(KeyGenerate.getOrderCode());
			order.setPhone(req.getPhone());
			order.setDistributorCode(dist.getDistrbutorCode());
			order.setCreateDate(new Date());
			order.setDistributorOrderCode(req.getOrder_id());
			order.setSize(req.getProduct_id());//流量包大小为流量大小以M为单位，1G为1024
			order.setState(CodeConstants.ORDER_STATE_INIT); 
			orderMapper.insert(order);
			
			return OrderResponse.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			PubLog.error("系统异常 ：>>" , e);
			return OrderResponse.SYS_ERR;
		}
		
	}
	
	/**
	 * 平台回调地址，上游通知平台订单结果
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/callback.do", method = RequestMethod.GET)
	@ResponseBody
	public OrderResponse callback(CallBackRequest req){
		OrderResponse resp = checkCallBackParam(req);
		//step1 :参数非空校验
		if(!StringUtils.isEmpty(resp.getMsg())){//非空校验
			PubLog.error("订单请求异常：【参数缺失】 >>" + resp);
			return resp;
		}
		
		//更新订单
		Order order = new Order();
		order.setOrderCode(req.getOrder_id());
		order.setProviderOrderCode(req.getP_order_id());
		order.setCallbackCode(req.getCode());
		order.setCallbackCodeMess(req.getMsg());
		order.setCallbackDate(new Date());
		if(CodeConstants.SUCCESS.equals(req.getCode())){
			order.setState(CodeConstants.ORDER_STATE_SUCC);
		}else{
			order.setState(CodeConstants.ORDER_STATE_ERR);
		}
		orderMapper.updateOrder(order);
		return OrderResponse.SUCCESS;
	}
	
	private OrderResponse checkCallBackParam(CallBackRequest req) {
		String msg = "";
		String code = "";
		if(StringUtils.isEmpty(req.getOrder_id())){
			msg = "平台订单号参数缺失,请检查请求！" ;
			code = CodeConstants.ARG_ERR_ORDER;
		}else if(StringUtils.isEmpty(req.getP_order_id())){
			msg = "上游订单号参数缺失,请检查请求！" ;
			code = CodeConstants.ARG_ERR_ORDER;
		}else if(StringUtils.isEmpty(req.getMsg())){
			msg = "返回描述参数缺失,请检查请求！" ;
			code = CodeConstants.ARG_ERR_ORDER;
		}else if(StringUtils.isEmpty(req.getCode())){
			msg = "响应码参数缺失,请检查请求！" ;
			code = CodeConstants.ARG_ERR_ORDER;
		}
		
		return new OrderResponse(code, msg);
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
	

}
