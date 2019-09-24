package com.shirospring.dao;

import com.shirospring.pojo.Permission;

import java.util.List;

public interface PermissionMapper {
	public List<Permission> listPermissionsByUserName(String userName);
	
}
