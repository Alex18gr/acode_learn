package com.alexc.demobs.dao;

import com.alexc.demobs.entity.User;

public interface UserDao {

    User findByUserName(String userName);

    void save(User user);

}
