package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;

import java.util.List;

/**
 * @author yy
 * @version 1.0
 */
public interface CheckGroupService {
    public void add(CheckGroup checkGroup, Integer[] checkitemIds);
    public PageResult pageQuery(QueryPageBean queryPageBean);
    public CheckGroup findById(int id);
//    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id);
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id);
    public void edit(CheckGroup checkGroup, Integer[] checkitemIds);
    public List<CheckGroup> findAll();
}
