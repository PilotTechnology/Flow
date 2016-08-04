package com.flow.system.service;

import java.util.Map;

import com.flow.pub.util.PageUtil;
import com.flow.system.model.RefundFlow;

public interface RefundFlowService {
	PageUtil<RefundFlow> listPage(Map<String, Object> map);

	RefundFlow getRefundFlowByPrimaryKey(Integer id);
	
	void save(RefundFlow refundFlow);

	void delete(RefundFlow refundFlow);

	void update(RefundFlow refundFlow);
	
	boolean checkExists(RefundFlow refundFlow);
}
