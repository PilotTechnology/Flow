package com.flow.system.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flow.portal.service.AbsPageService;
import com.flow.pub.util.PageUtil;
import com.flow.system.mapper.RefundFlowMapper;
import com.flow.system.model.RefundFlow;
import com.flow.system.service.RefundFlowService;
@Service
public class RefundFlowServiceImpl extends AbsPageService<RefundFlow> implements RefundFlowService {

	@Autowired
	private RefundFlowMapper refundFlowMapper;
	
	@Override
	public PageUtil<RefundFlow> listPage(Map<String, Object> map) {
		//分页参数获取
		PageUtil<RefundFlow> page = findPage(map);
		Long records = refundFlowMapper.getCount(map);
		page.setRecords(records);
		if(records > 0){
			map.put("start", page.getFirstResult());
			map.put("pageSize",page.getPageSize());
			page.setRows(refundFlowMapper.listPage(map));
		}
		return page;
	}

	@Override
	public RefundFlow getRefundFlowByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return refundFlowMapper.selectByPrimaryKey(id);
	}

	@Override
	public void save(RefundFlow refundFlow) {
		// TODO Auto-generated method stub
		refundFlowMapper.insert(refundFlow);
	}

	@Override
	public void delete(RefundFlow refundFlow) {
		// TODO Auto-generated method stub
		refundFlowMapper.deleteByPrimaryKey(refundFlow.getId());
	}

	@Override
	public void update(RefundFlow refundFlow) {
		// TODO Auto-generated method stub
		refundFlowMapper.updateByPrimaryKeySelective(refundFlow);
	}

	@Override
	public boolean checkExists(RefundFlow refundFlow) {
		RefundFlow oldRefundFlow = refundFlowMapper.selectByOrderCode(refundFlow.getOrderCode());
		if(oldRefundFlow!=null){
			return true;
		}else{
			return false;
		}
	}

}
