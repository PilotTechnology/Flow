package com.flow.system.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flow.portal.service.AbsPageService;
import com.flow.pub.util.PageUtil;
import com.flow.system.mapper.MobileMapper;
import com.flow.system.model.Mobile;
import com.flow.system.service.MobileService;

@Service
public class MobileServiceImpl extends AbsPageService<Mobile> implements MobileService {

	@Autowired
	private MobileMapper mobileMapper;
	
	@Override
	public PageUtil<Mobile> listPage(Map<String, Object> map) {
		//分页参数获取
		PageUtil<Mobile> page = findPage(map);
		Long records = mobileMapper.getCount(map);
		page.setRecords(records);
		if(records > 0){
			map.put("start", page.getFirstResult());
			map.put("pageSize",page.getPageSize());
			page.setRows(mobileMapper.listPage(map));
		}
		return page;
	}

	@Override
	public Mobile getMobilerByCode(String mobileCode) {
		// TODO Auto-generated method stub
		return mobileMapper.selectByMobileCode(mobileCode);
	}

	@Override
	public void save(Mobile mobile) {
		// TODO Auto-generated method stub
		mobileMapper.insert(mobile);
	}

	@Override
	public void delete(Mobile mobile) {
		// TODO Auto-generated method stub
		mobileMapper.deleteByPrimaryKey(mobile.getId());
	}

	@Override
	public void update(Mobile mobile) {
		// TODO Auto-generated method stub
		mobileMapper.updateByPrimaryKeySelective(mobile);
	}

	@Override
	public boolean checkExists(Mobile mobile) {
		Mobile oldMobile = mobileMapper.selectByMobileCode(mobile.getMobileCode());
		if(mobile.getId()!=null){
			return !(oldMobile.getId().equals(mobile.getId()));
		}else{
			return oldMobile != null;
		}
	}

}
