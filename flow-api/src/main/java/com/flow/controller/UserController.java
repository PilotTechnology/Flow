package com.flow.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.flow.model.User;
import com.flow.service.UserService;

/**
 * 对用户的操作类
 * @author MK
 *
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public List<User> findList(){
		List<User> userList = userService.findList();
		System.out.println("----end------");
		return userList;
	}
	
	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public User get(@Param("id") int id){
		return userService.findById(id);
	}
	
	@RequestMapping(value = "/put/{id}", method = RequestMethod.PUT)
	public User put(@PathVariable("id") int id){
		System.out.println("---- get ---- :" + id);
		return userService.findById(id);
	}
	
	@RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") int id){
		System.out.println("---- get ---- :" + id);
		userService.delete(id);
		
		return "success";
	}
	
	
}
