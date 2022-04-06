package com.itheima.dao;

import com.itheima.pojo.Permission;

import java.util.Set;

/**
 * @author yy
 * @version 1.0
 */
public interface PermissionDao {
    public Set<Permission> findByRoleId(int roleId);
}
