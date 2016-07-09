package com.flow.service;

import java.util.List;

import com.flow.model.User;

public interface UserService {

	int insert(User record);

	int delete(Integer id);

	int update(User record);

	User findById(int id);

	List<User> findList();
}
