package com.flow.portal;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.flow.pub.util.PageUtil;
import com.flow.system.bean.OrderBean;
import com.flow.system.service.OrderService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/applicationContext.xml",})
public class OrderTest {

	@Autowired
	private OrderService orderService;
	
	@Test
	public void testListPage(){
		Map<String,Object> map = new HashMap<String,Object>();
		PageUtil<OrderBean> orderList = orderService.listPage(map);
		System.out.println(orderList);
	}
}
