package com.flow.system.service;

import java.util.List;
import java.util.Map;

import com.flow.system.model.OrderStatistics;

public interface OrderStatisticsService {
	List<OrderStatistics> orderStatisticsByDistributor(Map<String, Object> map);
	List<OrderStatistics> orderStatisticsBySize(Map<String,Object> map);
	List<OrderStatistics> orderStatisticsByProvince(Map<String,Object> map);
	Long getCountOfToday(Map<String,Object> map);
	Long getCount(Map<String,Object> map);
	List<OrderStatistics> orderStatisticsByDay(Map<String,Object> map);
}
