package com.flow.system.service;

import java.util.Map;

import com.flow.pub.util.PageUtil;
import com.flow.system.model.CostFlow;

public interface CostFlowService {
	PageUtil<CostFlow> listPage(Map<String, Object> map);
	void save(CostFlow costFlow);
}
