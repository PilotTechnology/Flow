package com.flow.system.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flow.portal.service.AbsPageService;
import com.flow.pub.util.PageUtil;
import com.flow.system.mapper.DistributorMapper;
import com.flow.system.model.Distributor;
import com.flow.system.service.DistributorService;
@Service
public class DistributorServiceImpl extends AbsPageService<Distributor> implements DistributorService {

	@Autowired
	private DistributorMapper distributorMapper;
	
	@Override
	public PageUtil<Distributor> listPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		//分页参数获取
		PageUtil<Distributor> page = findPage(map);
		Long records = distributorMapper.getCount(map);
		page.setRecords(records);
		if(records > 0){
			map.put("start", page.getFirstResult());
			map.put("pageSize",page.getPageSize());
			page.setRows(distributorMapper.listPage(map));
		}
		return page;
	}

	@Override
	public Distributor getProviderByCode(String distributorCode) {
		// TODO Auto-generated method stub
		return distributorMapper.selectByDistributorCode(distributorCode);
	}

	public Double getBalance(String distributorCode) {
		// TODO Auto-generated method stub
		return distributorMapper.selectBanlanceByDistributorCode(distributorCode);
	}
	
	@Override
	public void addBalance(String distributorCode, Double balance) {
		// TODO Auto-generated method stub
		distributorMapper.addBalance(distributorCode, balance);
	}

	@Override
	public void save(Distributor distributor) {
		// TODO Auto-generated method stub
		distributorMapper.insert(distributor);
	}

	@Override
	public void delete(Distributor distributor) {
		// TODO Auto-generated method stub
		distributorMapper.deleteByDistributorCode(distributor.getDistrbutorCode());
	}

	@Override
	public void update(Distributor distributor) {
		// TODO Auto-generated method stub
		distributorMapper.updateByPrimaryKeySelective(distributor);
	}

	@Override
	public boolean checkExists(Distributor distributor) {
		// TODO Auto-generated method stub
		Distributor oldDistributor = distributorMapper.selectByDistributorCode(distributor.getDistrbutorCode());
		if(distributor.getId()!=null){
			return !(oldDistributor.getDistrbutorCode().equals(distributor.getDistrbutorCode()));
		}else{
			return oldDistributor != null;
		}
	}
}
