package com.shiroPermission.service;


import com.shiroPermission.pojo.Role;

public interface RolePermissionService {
    public void setPermissions(Role role, long[] permissionIds);
    public void deleteByRole(long roleId);
    public void deleteByPermission(long permissionId);
}