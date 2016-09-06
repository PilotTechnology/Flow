package com.flow.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flow.portal.service.AbsPageService;
import com.flow.system.mapper.OrderStatisticsMapper;
import com.flow.system.model.OrderStatistics;
import com.flow.system.service.OrderStatisticsService;

@Service
public class OrderStatisticsServiceImpl extends AbsPageService<OrderStatistics> implements OrderStatisticsService {

	@Autowired
	private OrderStatisticsMapper orderStatisticsMapper;
	
	@Override
	public List<OrderStatistics> orderStatisticsByDistributor(Map<String, Object> map) {
		return orderStatisticsMapper.orderStatisticsByDistributor(map);
	}
	
	@Override
	public List<OrderStatistics> orderStatisticsBySize(Map<String,Object> map){
		return orderStatisticsMapper.orderStatisticsBySize(map);
	}

	@Override
	public List<OrderStatistics> orderStatisticsByProvince(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return orderStatisticsMapper.orderStatisticsByProvince(map);
	}

	@Override
	public Long getCountOfToday(Map<String,Object> map){
		return orderStatisticsMapper.getCountOfToday(map);
	}
	
	@Override
	public Long getCount(Map<String,Object> map){
		return orderStatisticsMapper.getCount(map);
	}
	
	@Override
	public List<OrderStatistics> orderStatisticsByDay(Map<String,Object> map){
		return orderStatisticsMapper.orderStatisticsByDay(map);
	}
}
