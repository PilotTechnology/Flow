package com.flow.api.mapper;

import java.util.List;

import com.flow.system.model.Order;

public interface OrderApiMapper {
	/**
	 * 查询需要订购的订单列表
	 * @return
	 */
	List<Order> getOrderList();
	
	/**
	 * 查询需要回调的订单列表
	 * @return
	 */
	List<Order> getCallBackList();
	
	/**
	 * 根据orderId查询订单信息
	 * @param orderId
	 * @return
	 */
	Order getOrderByDistributorOrderId(String distributorOrderId);
	
    void increaseNoticeErrorTime(String orderCode);
    
    void distributorCallbackSucc(String orderCode);
}
