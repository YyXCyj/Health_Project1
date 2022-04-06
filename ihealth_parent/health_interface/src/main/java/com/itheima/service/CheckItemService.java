package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckItem;

import java.util.List;

/**
 * @author yy
 * @version 1.0
 */
public interface CheckItemService {
    public void add(CheckItem checkItem);

    public PageResult pageQuery(QueryPageBean queryPageBean);

    public void deleteById(int id);

    public void edit(CheckItem checkItem);

    public CheckItem findById(int id);

    public List<CheckItem> findAll();

}
