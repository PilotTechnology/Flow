package com.flow.system.mapper;

import java.util.List;

import com.flow.system.model.Operator;

public interface OperatorMapper {
	
	/**
	 * 获取运营商列表
	 * @return
	 */
	List<Operator> findList();
}