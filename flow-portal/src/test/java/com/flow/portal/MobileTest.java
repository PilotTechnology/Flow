package com.flow.portal;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.flow.system.service.MobileService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/applicationContext.xml",})
public class MobileTest {

	@Autowired
	private MobileService  mobileService;
	
	@Test
	public void getCityByCityIdTest(){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("start", 1);
		map.put("pageSize", 10);
		System.out.println(mobileService.listPage(map));
	}
}