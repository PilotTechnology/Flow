package com.flow.system.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flow.portal.service.AbsPageService;
import com.flow.pub.util.PageUtil;
import com.flow.system.mapper.CostFlowMapper;
import com.flow.system.model.CostFlow;
import com.flow.system.service.CostFlowService;
@Service
public class CostFlowServiceImpl extends AbsPageService<CostFlow> implements CostFlowService {

	@Autowired
	private CostFlowMapper costFlowMapper;
	
	@Override
	public PageUtil<CostFlow> listPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				//分页参数获取
				PageUtil<CostFlow> page = findPage(map);
				Long records = costFlowMapper.getCount(map);
				page.setRecords(records);
				if(records > 0){
					map.put("start", page.getFirstResult());
					map.put("pageSize",page.getPageSize());
					page.setRows(costFlowMapper.listPage(map));
				}
				return page;
	}

	@Override
	public void save(CostFlow costFlow) {
		// TODO Auto-generated method stub
		costFlowMapper.insert(costFlow);
	}

}
