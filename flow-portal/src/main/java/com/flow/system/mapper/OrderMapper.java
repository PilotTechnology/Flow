package com.flow.system.mapper;

import java.util.List;
import java.util.Map;

import com.flow.system.bean.OrderBean;

public interface OrderMapper {

	Long getCount(Map<String,Object> map);

	List<OrderBean> listPage(Map<String,Object> map);

	OrderBean selectByOrderCode(String orderCode);
}
