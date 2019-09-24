package com.shirospring.dao;



import com.shirospring.pojo.Role;

import java.util.List;

public interface RoleMapper {
	public List<Role> listRolesByUserName(String userName);
	
}
