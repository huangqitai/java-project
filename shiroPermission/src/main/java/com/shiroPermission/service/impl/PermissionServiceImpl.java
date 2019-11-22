package com.shiroPermission.service.impl;
 
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.shiroPermission.mapper.PermissionMapper;
import com.shiroPermission.mapper.RolePermissionMapper;
import com.shiroPermission.pojo.Permission;
import com.shiroPermission.pojo.PermissionExample;
import com.shiroPermission.pojo.Role;
import com.shiroPermission.pojo.RolePermission;
import com.shiroPermission.pojo.RolePermissionExample;
import com.shiroPermission.service.PermissionService;
import com.shiroPermission.service.RoleService;
import com.shiroPermission.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl  implements PermissionService {
 
    @Autowired
    PermissionMapper permissionMapper;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    RolePermissionMapper rolePermissionMapper;
 
    @Override
    public Set<String> listPermissions(String userName) {
        Set<String> result = new HashSet<>();
        List<Role> roles = roleService.listRoles(userName);
         
        List<RolePermission> rolePermissions = new ArrayList<>();
         
        for (Role role : roles) {
            RolePermissionExample example = new RolePermissionExample();
            example.createCriteria().andRidEqualTo(role.getId());
            List<RolePermission> rps= rolePermissionMapper.selectByExample(example);
            rolePermissions.addAll(rps);
        }
         
        for (RolePermission rolePermission : rolePermissions) {
            Permission p = permissionMapper.selectByPrimaryKey(rolePermission.getPid());
            result.add(p.getName());
        }
         
        return result;
    }
   @Override
    public void add(Permission u) {
        permissionMapper.insert(u);
    }
  
    @Override
    public void delete(Long id) {
        permissionMapper.deleteByPrimaryKey(id);
    }
  
    @Override
    public void update(Permission u) {
        permissionMapper.updateByPrimaryKeySelective(u);
    }
  
    @Override
    public Permission get(Long id) {
        return permissionMapper.selectByPrimaryKey(id);
    }
  
    @Override
    public List<Permission> list(){
        PermissionExample example =new PermissionExample();
        example.setOrderByClause("id desc");
        return permissionMapper.selectByExample(example);
  
    }
    @Override
    public List<Permission> list(Role role) {
        List<Permission> result = new ArrayList<>();
        RolePermissionExample example = new RolePermissionExample();
        example.createCriteria().andRidEqualTo(role.getId());
        List<RolePermission> rps = rolePermissionMapper.selectByExample(example);
        for (RolePermission rolePermission : rps) {
            result.add(permissionMapper.selectByPrimaryKey(rolePermission.getPid()));
        }
         
        return result;
    }
 
}