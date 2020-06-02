package com.qitai.springsecurity.dao;

import com.qitai.springsecurity.entity.Permission;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository(PermissionDao.BeanName)
public interface PermissionDao extends PagingAndSortingRepository<Permission, String>, JpaSpecificationExecutor<Permission> {

    public static final String BeanName="permissionDao";
}
