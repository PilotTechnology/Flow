package com.flow.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.flow.api.mapper.OrderApiMapper;
import com.flow.api.model.BalanceQueryRequest;
import com.flow.api.model.BalanceQueryResponse;
import com.flow.api.model.OrderQueryRequest;
import com.flow.api.model.OrderQueryResponse;
import com.flow.api.model.ProductForApi;
import com.flow.api.model.ProductQueryRequest;
import com.flow.api.model.ProductQueryResponse;
import com.flow.pub.common.CodeConstants;
import com.flow.pub.common.PubLog;
import com.flow.pub.util.MD5Util;
import com.flow.system.mapper.DistributorMapper;
import com.flow.system.mapper.ProductForDistributorMapper;
import com.flow.system.mapper.QuotationMapper;
import com.flow.system.model.Distributor;
import com.flow.system.model.Order;
import com.flow.system.model.ProductForDistributor;
import com.flow.system.model.Quotation;

/**
 * 查询订单、余额
 * @author MK
 *
 */
@RestController
@RequestMapping(value = "/api")
public class QueryApiController {
	@Autowired
	private OrderApiMapper orderApiMapper;
	
	@Autowired
	private DistributorMapper distributorMapper;
	
	@Autowired
	private ProductForDistributorMapper productForDistributorMapper;
	
	@Autowired
	private QuotationMapper quotationMapper;
	/**
	 * 查询订单
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/queryorder.do", method = RequestMethod.GET)
	@ResponseBody
	public OrderQueryResponse queryOrder(OrderQueryRequest req){
		try {
			OrderQueryResponse resp = checkOrderQueryParam(req);
			//step1 :参数非空校验
			if(!StringUtils.isEmpty(resp.getMsg())){//非空校验通过
				PubLog.error("订单查询请求异常：【参数缺失】 >>" + resp);
				return resp;
			}
			
			//step2:校验签名
			Distributor dist = distributorMapper.selectByAppKey(req.getAppkey());
			if(dist==null){
				resp.setCode(CodeConstants.ACC_ERR);
				resp.setMsg("订单查询请求异常：【未查找到有效的下游账户】 ");
				PubLog.error(resp.getMsg() + ">> " + resp);
				return resp;
			}
			String secret = dist.getSecretKey(); 
			String md5Src = String.format("order_id=%s&time=%s&secret=%s", 
											req.getOrder_id(), req.getTime(), secret);
			if(!MD5Util.EncodeString(md5Src).equalsIgnoreCase(req.getSign())){//签名校验失败
				resp.setCode(CodeConstants.ARG_ERR_SIGN);
				resp.setMsg("订单查询请求异常：【签名校验失败】 ");
				PubLog.error(resp.getMsg() + ">> " + resp);
				return resp;
			}
			Order order = orderApiMapper.getOrderByDistributorOrderId(req.getOrder_id());
			OrderQueryResponse orderQueryResponse = new OrderQueryResponse(order.getPlatformErrorCode(), "");
			orderQueryResponse.setOrderId(order.getDistributorOrderCode());
			orderQueryResponse.setPOrderId(order.getOrderCode());
			return orderQueryResponse;
		} catch (Exception e) {
			e.printStackTrace();
			PubLog.error("系统异常 ：>>" , e);
			return OrderQueryResponse.SYS_ERR;
		}
	}
	/**
	 * 校验查询订单请求参数是否缺失
	 * @param req
	 * @return Response
	 */
	private OrderQueryResponse checkOrderQueryParam(OrderQueryRequest req) {
		String msg = "";
		String code = "";
		if(StringUtils.isEmpty(req.getAppkey())){
			msg = "appkey参数缺失,请检查请求！" ;
			code = CodeConstants.ARG_ERR_KEY;
		}else if(StringUtils.isEmpty(req.getOrder_id())){
			msg = "order_id参数缺失,请检查请求！" ;
			code = CodeConstants.ARG_ERR_ORDER;
		}else if(StringUtils.isEmpty(req.getTime())){
			msg = "time参数缺失,请检查请求！" ;
			code = CodeConstants.ARG_ERR_TIME;
		}else if(StringUtils.isEmpty(req.getSign())){
			msg = "Sign参数缺失,请检查请求！" ;
			code = CodeConstants.ARG_ERR_SIGN;
		}
		
		return new OrderQueryResponse(code, msg);
		
	}
	
	/**
	 * 查询余额
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/querybalance.do", method = RequestMethod.GET)
	@ResponseBody
	public BalanceQueryResponse queryBalance(BalanceQueryRequest req){
		try {
			BalanceQueryResponse resp = checkBalanceQueryParam(req);
			//step1 :参数非空校验
			if(!StringUtils.isEmpty(resp.getMsg())){//非空校验通过
				PubLog.error("余额查询请求异常：【参数缺失】 >>" + resp);
				return resp;
			}
			
			//step2:校验签名
			Distributor dist = distributorMapper.selectByAppKey(req.getAppkey());
			if(dist==null){
				resp.setCode(CodeConstants.ACC_ERR);
				resp.setMsg("余额查询请求异常：【未查找到有效的下游账户】 ");
				PubLog.error(resp.getMsg() + ">> " + resp);
				return resp;
			}
			String secret = dist.getSecretKey(); 
			String md5Src = String.format("time=%s&secret=%s", req.getTime(), secret);
			if(!MD5Util.EncodeString(md5Src).equalsIgnoreCase(req.getSign())){//签名校验失败
				resp.setCode(CodeConstants.ARG_ERR_SIGN);
				resp.setMsg("余额查询请求异常：【签名校验失败】 ");
				PubLog.error(resp.getMsg() + ">> " + resp);
				return resp;
			}

			BalanceQueryResponse balanceQueryResponse = BalanceQueryResponse.SUCCESS;
			balanceQueryResponse.setBalance(dist.getBalance());
			return balanceQueryResponse;
		} catch (Exception e) {
			e.printStackTrace();
			PubLog.error("系统异常 ：>>" , e);
			return BalanceQueryResponse.SYS_ERR;
		}
	}
	/**
	 * 校验查询余额请求参数是否缺失
	 * @param req
	 * @return Response
	 */
	private BalanceQueryResponse checkBalanceQueryParam(BalanceQueryRequest req) {
		String msg = "";
		String code = "";
		if(StringUtils.isEmpty(req.getAppkey())){
			msg = "appkey参数缺失,请检查请求！" ;
			code = CodeConstants.ARG_ERR_KEY;
		}else if(StringUtils.isEmpty(req.getTime())){
			msg = "time参数缺失,请检查请求！" ;
			code = CodeConstants.ARG_ERR_TIME;
		}else if(StringUtils.isEmpty(req.getSign())){
			msg = "Sign参数缺失,请检查请求！" ;
			code = CodeConstants.ARG_ERR_SIGN;
		}
		
		return new BalanceQueryResponse(code, msg);
	}
	
	/**
	 * 查询流量包
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/queryproduct.do", method = RequestMethod.GET)
	@ResponseBody
	public ProductQueryResponse queryProduct(ProductQueryRequest req){
		try {
			ProductQueryResponse resp = checkProducrQueryParam(req);
			//step1 :参数非空校验
			if(!StringUtils.isEmpty(resp.getMsg())){//非空校验通过
				PubLog.error("流量包查询请求异常：【参数缺失】 >>" + resp);
				return resp;
			}
			
			//step2:校验签名
			Distributor dist = distributorMapper.selectByAppKey(req.getAppkey());
			if(dist==null){
				resp.setCode(CodeConstants.ACC_ERR);
				resp.setMsg("流量包查询请求异常：【未查找到有效的下游账户】 ");
				PubLog.error(resp.getMsg() + ">> " + resp);
				return resp;
			}
			String secret = dist.getSecretKey(); 
			String md5Src = String.format("operator=%s&time=%s&secret=%s", req.getOperator(), req.getTime(), secret);
			if(!MD5Util.EncodeString(md5Src).equalsIgnoreCase(req.getSign())){//签名校验失败
				resp.setCode(CodeConstants.ARG_ERR_SIGN);
				resp.setMsg("流量包查询请求异常：【签名校验失败】 ");
				PubLog.error(resp.getMsg() + ">> " + resp);
				return resp;
			}
			
			Quotation quotation = quotationMapper.getQuotationByDistributorCode(dist.getDistrbutorCode());
			if (quotation == null) {
				resp.setCode(CodeConstants.ACC_ERR_NO_QUOTATION);
				resp.setMsg("流量包查询请求异常：【尚未配置报价单】 ");
				PubLog.error(resp.getMsg() + ">> " + resp);
				return resp;
			}
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("quotationCode", quotation.getServiceCode());
			map.put("operatorCode", req.getOperator());
			List<ProductForDistributor> list = productForDistributorMapper.getProducts(map);
			List<ProductForApi> productList = new ArrayList<ProductForApi>();
			for (ProductForDistributor productForDistributor : list) {
				ProductForApi product = new ProductForApi();
				product.setOperator(productForDistributor.getOperatorName());
				product.setSize(productForDistributor.getSize());
				product.setPrice(productForDistributor.getPrice());
				product.setDiscount(productForDistributor.getDiscount());
				if (productForDistributor.getEnableArea() != 0) {
					product.setProvince(productForDistributor.getProvinceName());
				}
				
				productList.add(product);
			}

			ProductQueryResponse productQueryResponse = ProductQueryResponse.SUCCESS;
			productQueryResponse.setProductList(productList);
			
			return productQueryResponse;
		} catch (Exception e) {
			e.printStackTrace();
			PubLog.error("系统异常 ：>>" , e);
			return ProductQueryResponse.SYS_ERR;
		}
	}
	/**
	 * 校验查询流量包请求参数是否缺失
	 * @param req
	 * @return Response
	 */
	private ProductQueryResponse checkProducrQueryParam(ProductQueryRequest req) {
		String msg = "";
		String code = "";
		if(StringUtils.isEmpty(req.getAppkey())){
			msg = "appkey参数缺失,请检查请求！" ;
			code = CodeConstants.ARG_ERR_KEY;
		}else if(StringUtils.isEmpty(req.getOperator())){
			msg = "operator参数缺失,请检查请求！" ;
			code = CodeConstants.ARG_ERR_OPERATOR;
		}else if(StringUtils.isEmpty(req.getTime())){
			msg = "time参数缺失,请检查请求！" ;
			code = CodeConstants.ARG_ERR_TIME;
		}else if(StringUtils.isEmpty(req.getSign())){
			msg = "sign参数缺失,请检查请求！" ;
			code = CodeConstants.ARG_ERR_SIGN;
		}
		
		return new ProductQueryResponse(code, msg);
	}
}