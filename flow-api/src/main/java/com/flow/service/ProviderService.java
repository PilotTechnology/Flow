package com.flow.service;

import java.util.List;

import com.flow.model.Provider;

public interface ProviderService {
	int insert(Provider record);

	int delete(Integer id);

	int update(Provider record);

	Provider findById(int id);

	List<Provider> findList();
}
