package com.alexc.demobs.service;

import com.alexc.demobs.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByUserName(String userName);

}
