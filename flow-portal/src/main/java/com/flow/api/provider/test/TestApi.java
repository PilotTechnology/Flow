package com.flow.api.provider.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.flow.api.http.HttpClientUtil;
import com.flow.api.util.OrderUtil;
import com.flow.pub.common.CodeConstants;
import com.flow.pub.common.PubLog;
import com.flow.pub.util.MD5Util;
import com.flow.system.model.Order;
@Service
public class TestApi {
	
	public static final String TEST_API_ORDER_URL = "http://192.168.1.105:8080/api/order.do";
	public static final String TEST_API_APPKEY = "f73795397db04e0da2e500ca86d3cb14";
	public static final String TEST_API_SECRET = "123afa048c58456d9b4d561fc624b38d";

	@Autowired
	private OrderUtil orderUtil;
	/**
	 * 新的上游请求
	 * @param order
	 */
	public void newOrder(Order order){
		try { 
			Map<String,String> param = new HashMap<>();
			param.put("appkey", TEST_API_APPKEY);
			param.put("phone", order.getPhone());
			param.put("order_id", order.getOrderCode());
			param.put("scope", String.valueOf(order.getEnableArea()));
			param.put("product_id", String.valueOf(order.getSize()));
			String time = String.valueOf(System.currentTimeMillis());
			param.put("time", time);
			String sign = MD5Util.EncodeString("phone="+order.getPhone()+"&product_id="+order.getSize()+
						"&scope="+order.getEnableArea()+"&order_id="+order.getOrderCode()+"&time="+time+"&secret=" + TEST_API_SECRET);

			param.put("sign", sign);
			String response = HttpClientUtil.httpGetRequest(TEST_API_ORDER_URL, param);
			System.out.println("response : " + response);
			JSONObject jsonObj = JSONObject.parseObject(response);
			System.out.println("result json : >> " + jsonObj);
			String code = jsonObj.getString("code");
//			String order_id = jsonObj.getString("order_id");
//			String p_order_id = jsonObj.getString("p_order_id");
			String msg = jsonObj.getString("msg");
			if(CodeConstants.SUCCESS.equals(code)){//响应成功
				orderUtil.updateOrder(CodeConstants.ORDER_STATE_SUBMIT_SUCC, order.getOrderCode(), null, code, msg);
			}else{
				orderUtil.updateOrder(CodeConstants.ORDER_STATE_SUBMIT_ERR, order.getOrderCode(), null, code, msg);
//				orderUtil.refund(order.getOrderCode());
			}
		} catch (Exception e) {
			PubLog.error("发送此上游异常！",e);
		} 
	}
	
}
