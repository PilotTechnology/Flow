package com.flow.system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.flow.system.model.Order;

public interface OrderMapper {

	Long getCount(Map<String,Object> map);

	List<Order> listPage(Map<String,Object> map);

	Order selectByOrderCode(String orderCode);
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
	 * 根据上游回调更新订单信息
	 * @param order
	 */
//	void updateOrder(Order order);
	
	void updateOrderState();

    int insert(Order record);

    int insertSelective(Order record);

    int updateByOrderCodeSelective(Order record);

    int updateByPrimaryKey(Order record);
}
