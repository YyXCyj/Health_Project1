package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.CheckItemDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yy
 * @version 1.0
 */

@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckItemDao checkItemDao;

    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);

    }

    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage,pageSize);
        Page<CheckItem> page = checkItemDao.selectByCondition(queryString);
        long total = page.getTotal();
        List<CheckItem> rows = page.getResult();
        return new PageResult(total,rows);
    }


    public void deleteById(int id) {
        long countByCheckItemId = checkItemDao.findCountByCheckItemId(id);
        if(countByCheckItemId>0){
            new RuntimeException();
        }
        checkItemDao.deleteById(id);
    }


    public void edit(CheckItem checkItem) {
        checkItemDao.edit(checkItem);
    }


    public CheckItem findById(int id) {
        return checkItemDao.findById(id);
    }


    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }
}
