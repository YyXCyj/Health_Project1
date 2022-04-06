package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckGroup;

import java.util.List;
import java.util.Map;

/**
 * @author yy
 * @version 1.0
 */
public interface CheckGroupDao {
    public void add(CheckGroup checkGroup);
    public void setCheckAndCheck(Map map);
    public Page<CheckGroup> findByCondition(String queryString);
    public CheckGroup findById(int id);
//    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id);
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id);
    public void edit(CheckGroup checkGroup);
    public void deleteAssocication(Integer checkGroupId);
    public List<CheckGroup> findAll();
}
