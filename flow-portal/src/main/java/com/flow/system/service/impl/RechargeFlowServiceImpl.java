package com.flow.system.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flow.portal.service.AbsPageService;
import com.flow.pub.util.PageUtil;
import com.flow.system.mapper.RechargeFlowMapper;
import com.flow.system.model.RechargeFlow;
import com.flow.system.service.RechargeFlowService;
@Service
public class RechargeFlowServiceImpl extends AbsPageService<RechargeFlow> implements RechargeFlowService {

	@Autowired
	private RechargeFlowMapper rechargeFlowMapper;
	
	@Override
	public PageUtil<RechargeFlow> listPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		//分页参数获取
		PageUtil<RechargeFlow> page = findPage(map);
		Long records = rechargeFlowMapper.getCount(map);
		page.setRecords(records);
		if(records > 0){
			map.put("start", page.getFirstResult());
			map.put("pageSize",page.getPageSize());
			page.setRows(rechargeFlowMapper.listPage(map));
		}
		return page;
	}

	@Override
	public void save(RechargeFlow rechargeFlow) {
		// TODO Auto-generated method stub
		rechargeFlowMapper.insertSelective(rechargeFlow);
	}

}
