package com.itheima.dao;

import com.itheima.pojo.Role;

import java.util.Set;

/**
 * @author yy
 * @version 1.0
 */
public interface RoleDao {
    public Set<Role> findByUserId(int id);
}
