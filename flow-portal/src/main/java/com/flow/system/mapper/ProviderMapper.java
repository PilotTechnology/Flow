package com.flow.system.mapper;

import java.util.List;
import java.util.Map;

import com.flow.system.model.Provider;

public interface ProviderMapper {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByProviderCode(String providerCode);

    int insert(Provider record);

    int insertSelective(Provider record);

    Provider selectByPrimaryKey(Integer id);
    
    Provider selectByProviderCode(String providerCode);

    int updateByPrimaryKeySelective(Provider record);

    int updateByPrimaryKey(Provider record);
    
    List<Provider> listPage(Map<String, Object> map);

	Long getCount(Map<String, Object> map);
}