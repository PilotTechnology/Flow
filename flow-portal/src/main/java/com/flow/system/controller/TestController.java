package com.flow.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flow.portal.controller.BaseController;
import com.flow.system.model.SysRole;

@Controller
@RequestMapping("/portal")
public class TestController extends BaseController{

	@RequestMapping(value = "test!init.action")
	public String init(){
		return "/view/test/testList.jsp";
	}
	
	@RequestMapping(value = "test!list.action")
	@ResponseBody
	public Map<String,Object> list(){
		List<SysRole> list  = new ArrayList<>();
		SysRole role1 = new SysRole();
		role1.setId(1);role1.setRoleCode("test1");
		role1.setRoleName("测试1");role1.setCreateUser("system");
		
		SysRole role2 = new SysRole();
		role2.setId(2);role2.setRoleCode("test2");
		role2.setRoleName("测试2");role2.setCreateUser("admin");
		
		list.add(role1);list.add(role2);
		Map<String,Object> info = new HashMap<>();
		 info.put("data", list);
		    info.put("recordsTotal", "10");
		    info.put("recordsFiltered", "10");
		    info.put("draw", "1");
		return info;
	}
	
}
