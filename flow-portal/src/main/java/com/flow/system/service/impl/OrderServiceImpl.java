package com.flow.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.flow.portal.service.AbsPageService;
import com.flow.pub.util.PageUtil;
import com.flow.system.bean.OrderBean;
import com.flow.system.mapper.MobileMapper;
import com.flow.system.mapper.OrderMapper;
import com.flow.system.service.OrderService;

@Service
public class OrderServiceImpl extends AbsPageService<OrderBean> implements OrderService{

	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private MobileMapper mobileMapper;
	
	/**
	 * 分页展示
	 * @param map : 查询条件
	 */
	public PageUtil<OrderBean> listPage(Map<String, Object> map) {
		PageUtil<OrderBean> page = findPage(map);
		
		Long records = orderMapper.getCount(map);
		page.setRecords(records);
		
		if(records > 0){
			map.put("start", page.getFirstResult());
			map.put("pageSize",page.getPageSize());
			List<OrderBean> list = orderMapper.listPage(map);
			
			for(OrderBean ob : list){
				if(StringUtils.isEmpty(ob.getPhone())){
					ob.setProviderName(mobileMapper.getMobile(ob.getPhone().substring(0, 7)));
				}
			}
			page.setRows(list);
		}
		
		return page;
	}

}
