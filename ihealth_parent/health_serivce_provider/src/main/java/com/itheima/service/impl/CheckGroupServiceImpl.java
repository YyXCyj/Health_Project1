package com.itheima.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.CheckGroupDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;
import com.itheima.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yy
 * @version 1.0
 */
@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {
    @Autowired
    public CheckGroupDao checkGroupDao;

    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        checkGroupDao.add(checkGroup);
        Integer checkGroupId = checkGroup.getId();
        if(checkitemIds!=null && checkitemIds.length>0){
            for(Integer checkitemId :checkitemIds){
                Map<String,Integer> map =new HashMap<>();
                map.put("checkGroupId",checkGroupId);
                map.put("checkitemIds",checkitemId);
                checkGroupDao.setCheckAndCheck(map);
            }
        }


    }

    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        String queryString = queryPageBean.getQueryString();
        Integer pageSize = queryPageBean.getPageSize();
        PageHelper.startPage(currentPage, pageSize);
        Page<CheckGroup> page=checkGroupDao.findByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }


    public CheckGroup findById(int id) {
        return checkGroupDao.findById(id);
    }


    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id) {
        return checkGroupDao.findCheckItemIdsByCheckGroupId(id);

    }


    public void edit(CheckGroup checkGroup, Integer[] checkitemIds) {

        checkGroupDao.edit(checkGroup);

        Integer checkGroupId = checkGroup.getId();

        checkGroupDao.deleteAssocication(checkGroupId);

        this.setCheckAndCheck(checkGroupId,checkitemIds);
    }


    public List<CheckGroup> findAll() {
        return checkGroupDao.findAll();
    }

    public void setCheckAndCheck(Integer checkGroupId,Integer[] checkitemIds){
        if(checkitemIds!=null && checkitemIds.length>0){
            for(Integer checkitemId :checkitemIds){
                Map<String,Integer> map =new HashMap<>();
                map.put("checkGroupId",checkGroupId);
                map.put("checkitemIds",checkitemId);
                checkGroupDao.setCheckAndCheck(map);
            }
        }
    }






}
