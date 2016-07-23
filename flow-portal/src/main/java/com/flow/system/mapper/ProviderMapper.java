package com.flow.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
    
    List<Provider> listPage(@Param("start") Integer start , @Param("pageSize") Integer pageSize);

	Long getCount();
}