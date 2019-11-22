package com.shirossm.mapper;

import java.util.List;

import com.shirossm.pojo.Permission;

public interface PermissionMapper {
	public List<Permission> listPermissionsByUserName(String userName);
	
}
