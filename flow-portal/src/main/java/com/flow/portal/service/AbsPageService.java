package com.flow.portal.service;

import java.util.Map;

import org.springframework.util.StringUtils;

import com.flow.pub.util.PageUtil;

public class AbsPageService<T> {
	
	protected PageUtil<T> findPage(Map<String,Object> map){
		PageUtil<T> pager = new PageUtil<T>();
		
		String page = String.valueOf(map.get("page"));
		if(!StringUtils.isEmpty(page) && !"null".equals(page)){
			pager.setPage(Integer.valueOf(page));
		}
		
		String pageSize = String.valueOf(map.get("pageSize"));
		if(!StringUtils.isEmpty(pageSize) && !"null".equals(pageSize)){
			pager.setPageSize(Integer.valueOf(pageSize));
		}
		
		return pager;
	}
}
