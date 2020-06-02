package com.qitai.springsecurity.service;

import com.qitai.springsecurity.dao.PermissionDao;
import com.qitai.springsecurity.dao.RoleDao;
import com.qitai.springsecurity.dao.UserDao;
import com.qitai.springsecurity.entity.Role;
import com.qitai.springsecurity.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    public User getUser(String id){
        return userDao.findOne(id);
    }

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private DataSource dataSource;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException, DataAccessException {

        Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();

        User user = userDao.findUserByName(username);
        Set<Role> roles = user.getRoles();
        for(Role r: roles){
            System.out.println("UserRole : "+r);
            auths.add(new SimpleGrantedAuthority(r.getRole_name()));
        }

        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
                true, true, true, true, auths);
    }


    //set DataSource
    public void setDataSource( DataSource dataSource ){
        this.dataSource = dataSource;
    }

    public DataSource getDataSource(){
        return dataSource;
    }

}
