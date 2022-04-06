package com.itheima.dao;

import com.itheima.pojo.User;

/**
 * @author yy
 * @version 1.0
 */
public interface UserDao {
    public User findByUsername(String username);
}
