package com.flow.portal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.flow.system.bean.UserInfo;
import com.flow.system.service.RoleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/applicationContext.xml",})
public class RoleTest {

	@Autowired
	private RoleService roleService;
	
	@Test
	public void grantTest(){
		String roleCode = "admin";
		String menuCodes = "1,2,3";
		UserInfo user = new UserInfo();
		user.setUserCode("admin");
		roleService.grant(roleCode, menuCodes, user);
		
	}
}
