package com.flow.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.flow.system.model.SysRole;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

	List<SysRole> listPage(@Param("start") Integer start , @Param("pageSize") Integer pageSize);

	SysRole getRoleByCode(String roleCode);

	Long getCount();
}