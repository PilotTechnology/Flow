package com.flow.system.service;

import java.util.Map;

import com.flow.pub.util.PageUtil;
import com.flow.system.bean.OrderBean;

public interface OrderService {

	PageUtil<OrderBean> listPage(Map<String, Object> map);

}
