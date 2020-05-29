package com.qitai.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qitai.entity.User;
import com.qitai.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Role;


@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String name)
            throws UsernameNotFoundException {
        List list = jdbcTemplate.queryForList("select * from security_user where name='"+name+"'");
        User user = new User();
        user.setId(Integer.parseInt((String)((Map)list.get(0)).get("ID")));
        user.setName((String)((Map)list.get(0)).get("NAME"));
        user.setPassword((String)((Map)list.get(0)).get("PASSWORD"));
        List listRole = jdbcTemplate.queryForList("select r.* from security_role r, security_user_role ur where r.id= ur.user_id and ur.user_id='"+user.getId()+"'");
        //user.setUserProfiles((Set)((Map)((Map) list.get(0)).get("")));
        Set<UserRole> set = new HashSet();
        for (Object s :listRole ) {
            UserRole role = new UserRole();
            role.setId(Integer.parseInt((String)((Map)s).get("ID")));
            role.setRole_name((String)((Map)s).get("role_name"));
            role.setRole_code((String)((Map)s).get("role_code"));
            set.add(role);
        }
        user.setUserProfiles(set);
        System.out.println("User : "+user);
        if(user==null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
                true, true, true, true, getGrantedAuthorities(user));
    }


    private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for(UserRole userProfile : user.getUserProfiles()){
            System.out.println("UserProfile : "+userProfile);
            authorities.add(new SimpleGrantedAuthority(userProfile.getRole_name()));
        }
        System.out.print("authorities :"+authorities);
        return authorities;
    }

}

