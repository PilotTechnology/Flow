package com.flow.system.service;

import java.util.Map;

import com.flow.pub.util.PageUtil;
import com.flow.system.model.Order;

public interface OrderService {

	PageUtil<Order> listPage(Map<String, Object> map);

	Order getOrderByCode(String orderCode);
}
