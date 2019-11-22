package com.shiroPermission.service;


import com.shiroPermission.pojo.User;

public interface UserRoleService {
 
    public void setRoles(User user, long[] roleIds);
    public void deleteByUser(long userId);
    public void deleteByRole(long roleId);
     
}