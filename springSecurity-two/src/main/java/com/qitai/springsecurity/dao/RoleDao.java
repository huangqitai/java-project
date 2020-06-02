package com.qitai.springsecurity.dao;

import com.qitai.springsecurity.entity.Permission;
import com.qitai.springsecurity.entity.Role;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(RoleDao.BeanName)
public interface RoleDao extends PagingAndSortingRepository<Role, String>, JpaSpecificationExecutor<Role> {

    public static final String BeanName="roleDao";

}
