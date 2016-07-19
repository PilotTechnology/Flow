package com.flow.portal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.flow.system.service.ProvinceService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/applicationContext.xml",})
public class ProvinceTest {
	
	@Autowired
	private ProvinceService provinceService;
	
	@Test
	public void getProvinceTest(){
		System.out.println(provinceService.getProvinceByCode(4));
	}
}
