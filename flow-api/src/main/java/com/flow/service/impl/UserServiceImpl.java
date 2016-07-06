package com.flow.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flow.mapper.UserMapper;
import com.flow.model.User;
import com.flow.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper mapper;

	public int insert(User record) {
		return mapper.insert(record);
	}

	public int delete(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}

	public int update(User record) {
		return mapper.updateByPrimaryKey(record);
	}

	public User findById(int id) {
		return mapper.selectByPrimaryKey(id);
	}

	public List<User> findList() {
		return mapper.findPage();
	}
}
