package com.flow.mapper;

import org.apache.ibatis.annotations.Param;

import com.flow.model.Order;

/**
 * 订单
 * @author MK
 */
public interface OrderMapper {

	/**
	 * 根据appkey获取密钥
	 * @param appKey
	 * @return
	 */
	public String getSecretByAppKey(String appKey);
	
	/**
	 * 判断订单是否重复
	 * @param appkey
	 * @param order_id
	 * @return
	 */
	public int getOrderByAppKey(@Param("app_key") String appkey, @Param("order_id") String order_id);
	
	/**
	 * 订单初始信息入库
	 * @param order
	 * @return
	 */
	public int insert(Order order);

}
