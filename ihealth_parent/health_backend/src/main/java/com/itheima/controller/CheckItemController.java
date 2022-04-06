package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckItemService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yy
 * @version 1.0
 */
@RestController
@RequestMapping("/checkitem")
public class CheckItemController {

//    @Reference
    @Reference
    private CheckItemService checkItemService;


    //新增
    @PreAuthorize("hasAuthority('CHECKITEM_ADD')")//权限校验
    @RequestMapping("/add")
    public Result add(@RequestBody CheckItem checkItem){
        try{
            checkItemService.add(checkItem);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }


    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return checkItemService.pageQuery(queryPageBean);
    }
    //删除
    @PreAuthorize("hasAuthority('CHECKITEM_DELETE')")//权限校验
    @RequestMapping("/delete")
    public Result delete(Integer id){
        try{
            checkItemService.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }
    //编辑
    @PreAuthorize("hasAuthority('CHECKITEM_EDIT')")//权限校验
    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckItem checkItem){
        try{
            checkItemService.edit(checkItem);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
        return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
    }
    @RequestMapping("/findById")
    public Result findById(Integer id){
        try{
            CheckItem checkItem = checkItemService.findById(id);
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItem);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    @RequestMapping("/findAll")
    public Result findAll(){
        try{
            List<CheckItem> list = checkItemService.findAll();
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,list);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }





}


















