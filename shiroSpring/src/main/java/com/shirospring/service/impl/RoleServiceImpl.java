package com.shirospring.service.impl;


import com.shirospring.dao.RoleMapper;
import com.shirospring.pojo.Role;
import com.shirospring.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl  implements RoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public Set<String> listRoles(String userName) {
		List<Role> roles = roleMapper.listRolesByUserName(userName);
		Set<String> result = new HashSet<>();
		for (Role role: roles) {
			result.add(role.getName());
		}
		return result;
	}
}
