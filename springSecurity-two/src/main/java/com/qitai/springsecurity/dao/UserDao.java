package com.qitai.springsecurity.dao;

import com.qitai.springsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository(UserDao.BeanName)
public interface UserDao extends PagingAndSortingRepository<User, String>, JpaSpecificationExecutor<User> {
    public static final String BeanName="userDao";

    User findUserByName(String name);
}
