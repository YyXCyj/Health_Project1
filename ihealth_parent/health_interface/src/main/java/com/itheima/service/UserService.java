package com.itheima.service;

import com.itheima.pojo.User;

/**
 * @author yy
 * @version 1.0
 */
public interface UserService {
    public User findByUsername(String username);
}
