package com.flow.system.service;

import java.util.Map;

import com.flow.pub.util.PageUtil;
import com.flow.system.model.RechargeFlow;

public interface RechargeFlowService {
	PageUtil<RechargeFlow> listPage(Map<String, Object> map);
	
	void save(RechargeFlow rechargeFlow);
}
