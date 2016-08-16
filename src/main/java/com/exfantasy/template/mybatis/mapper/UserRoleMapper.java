package com.exfantasy.template.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.exfantasy.template.mybatis.model.UserRole;

@Mapper
public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);
}