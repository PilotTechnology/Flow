package com.flow.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flow.system.mapper.OperatorMapper;
import com.flow.system.mapper.ProviderMapper;
import com.flow.system.mapper.ProvinceMapper;
import com.flow.system.model.Operator;
import com.flow.system.model.Provider;
import com.flow.system.model.Province;

@Controller
@RequestMapping("/pub")
public class PublicController {
	
	@Autowired 
	private ProviderMapper providerMapper;
	
	@Autowired
	private OperatorMapper operatorMapper;
	
	@Autowired
	private ProvinceMapper provinceMapper;
	/**
	 * 获取供应商列表
	 * @return
	 */
	@RequestMapping(value = "provider!list.action")
	@ResponseBody
	public List<Provider> getProvider(){
		return providerMapper.listPage(null);
	}
	
	/**
	 * 获取运营商列表
	 * @return
	 */
	@RequestMapping(value = "operator!list.action")
	@ResponseBody
	public List<Operator> getOperator(){
		return operatorMapper.findList();
	}
	
	/**
	 * 获取省份列表
	 * @return
	 */
	@RequestMapping(value = "province!list.action")
	@ResponseBody
	public List<Province> getProvince(){
		return provinceMapper.findList();
	}
	
}
