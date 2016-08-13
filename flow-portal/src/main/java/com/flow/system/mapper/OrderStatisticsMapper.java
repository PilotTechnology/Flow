package com.flow.system.mapper;

import java.util.List;
import java.util.Map;
import com.flow.system.model.OrderStatistics;

public interface OrderStatisticsMapper {

	List<OrderStatistics> orderStatisticsByDistributor(Map<String,Object> map);
	
	List<OrderStatistics> orderStatisticsBySize(Map<String,Object> map);
	
	List<OrderStatistics> orderStatisticsByProvince(Map<String,Object> map);
}