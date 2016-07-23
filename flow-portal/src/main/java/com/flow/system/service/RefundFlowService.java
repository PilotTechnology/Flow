package com.flow.system.service;

import java.util.Map;

import com.flow.pub.util.PageUtil;
import com.flow.system.model.RefundFlow;

public interface RefundFlowService {
	PageUtil<RefundFlow> listPage(Map<String, Object> map);

	RefundFlow getRefundFlowByCode(Integer refundFlowCode);
	
	void save(RefundFlow distributor);

	void delete(RefundFlow distributor);

	void update(RefundFlow distributor);
	
	boolean checkExists(RefundFlow distributor);
}
