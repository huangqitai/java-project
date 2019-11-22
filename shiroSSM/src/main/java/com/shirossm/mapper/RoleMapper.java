package com.shirossm.mapper;

import java.util.List;

import com.shirossm.pojo.Role;

public interface RoleMapper {
	public List<Role> listRolesByUserName(String userName);
	
}
