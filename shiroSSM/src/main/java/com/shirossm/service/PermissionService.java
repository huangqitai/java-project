package com.shirossm.service;

import java.util.Set;

public interface PermissionService {
	public Set<String> listPermissions(String userName);
}