package com.flow.portal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flow.pub.common.Constant;
import com.flow.system.bean.UserInfo;
import com.flow.system.mapper.DistributorMapper;
import com.flow.system.mapper.OperatorMapper;
import com.flow.system.mapper.ProviderMapper;
import com.flow.system.mapper.ProvinceMapper;
import com.flow.system.model.Distributor;
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
	
	@Autowired
	private DistributorMapper distributorMapper;
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
	
	/**
	 * 获取分销商列表
	 * @return
	 */
	@RequestMapping(value = "distributor!list.action")
	@ResponseBody
	public List<Distributor> getDistributor(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		Distributor distributor = distributorMapper.selectByUserCode(userInfo.getUserCode());
		if (userInfo.getRoleCode().equals(Constant.DISTRIBUTOR_ROLE_CODE)) {
			if (distributor != null) {
				map.put("fatherDistributorCode", distributor.getDistrbutorCode());
			}
		} if (userInfo.getRoleCode().equals(Constant.SON_DISTRIBUTOR_ROLE_CODE)) {
			map.put("certainDistributor", distributor.getDistrbutorCode());
		}
		
		List<Distributor> distributorList = distributorMapper.listPage(map);
		if (distributor != null && userInfo.getRoleCode().equals(Constant.DISTRIBUTOR_ROLE_CODE)) {
			distributorList.add(distributor);
		}
		
		return distributorList;
	}
}
