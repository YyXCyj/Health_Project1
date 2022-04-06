package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Setmeal;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author yy
 * @version 1.0
 */

public interface SetmealService {
    public void add(Setmeal setmeal,Integer[] checkgroupIds);
    public PageResult pageQuery(QueryPageBean queryPageBean);
    public List<Setmeal> findAll();
    public Setmeal findById(int id);
    public List<Map<String,Object>> findSetmealCount();

}
