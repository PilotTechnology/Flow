package com.flow.mapper;

import com.flow.model.Distributor;

public interface DistributorMapper {
    
    Distributor selectByAppKey(String distributorCode);

}