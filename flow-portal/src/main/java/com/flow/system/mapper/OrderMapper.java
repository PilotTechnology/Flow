package com.flow.system.mapper;

import java.util.List;
import java.util.Map;

import com.flow.system.bean.OrderBean;

public interface OrderMapper {

	Long getCount();

	List<OrderBean> listPage(Map map);

}
