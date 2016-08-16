package com.flow.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flow.portal.service.AbsPageService;
import com.flow.pub.util.PageUtil;
import com.flow.system.mapper.OrderMapper;
import com.flow.system.model.Order;
import com.flow.system.service.OrderService;

@Service
public class OrderServiceImpl extends AbsPageService<Order> implements OrderService{

	@Autowired
	private OrderMapper orderMapper;
	
	/**
	 * 分页展示
	 * @param map : 查询条件
	 */
	public PageUtil<Order> listPage(Map<String, Object> map) {
		PageUtil<Order> page = findPage(map);
		Long records = orderMapper.getCount(map);
		page.setRecords(records);
		
		if(records > 0){
			map.put("start", page.getFirstResult());
			map.put("pageSize",page.getPageSize());
			List<Order> list = orderMapper.listPage(map);
			page.setRows(list);
		}
		
		return page;
	}

	public Order getOrderByCode(String orderCode){
		return orderMapper.selectByOrderCode(orderCode);
	}
}
