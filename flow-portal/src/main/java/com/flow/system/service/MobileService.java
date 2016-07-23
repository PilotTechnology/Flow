package com.flow.system.service;

import java.util.Map;

import com.flow.pub.util.PageUtil;
import com.flow.system.model.Mobile;

public interface MobileService {
	PageUtil<Mobile> listPage(Map<String, Object> map);

	Mobile getMobilerByCode(String mobileCode);
	
	void save(Mobile mobile);

	void delete(Mobile mobile);

	void update(Mobile mobile);
	
	boolean checkExists(Mobile mobile);
}
