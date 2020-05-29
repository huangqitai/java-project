package com.qitai.dao;

import com.qitai.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends PagingAndSortingRepository<User, String> {

    User findUserByName(String name);
}
