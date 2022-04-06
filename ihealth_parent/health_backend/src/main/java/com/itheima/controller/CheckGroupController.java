package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckGroup;
import com.itheima.service.CheckGroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yy
 * @version 1.0
 */
@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {
    @Reference
    public CheckGroupService checkGroupService;

    @RequestMapping("/add")
    public Result add(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds){
        try{
            checkGroupService.add(checkGroup,checkitemIds);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }
        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = checkGroupService.pageQuery(queryPageBean);
        return pageResult;
    }

    @RequestMapping("/findById")
    public Result findById(Integer id){
        try{
            CheckGroup checkGroup= checkGroupService.findById(id);
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroup);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }

    }



//    @RequestMapping("/findCheckItemIdsByCheckGroupId")
//    public Result findCheckItemIdsByCheckGroupId(Integer id){
//        try{
//            List<Integer> checkitemIds = checkGroupService.findCheckItemIdsByCheckGroupId(id);
//            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,checkitemIds);
//        }catch (Exception e){
//            e.printStackTrace();
//            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
//        }
//
//    }
    @RequestMapping("/findCheckItemIdsByCheckGroupId")
    public Result findCheckItemIdsByCheckGroupId(Integer id){
        try{
            List<Integer> checkitemIds =
                    checkGroupService.findCheckItemIdsByCheckGroupId(id);
            return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkitemIds);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }


    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds){
        try{
            checkGroupService.edit(checkGroup,checkitemIds);
            return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
    }
    @RequestMapping("/findAll")
    public Result findAll(){
        try{
            List<CheckGroup> list = checkGroupService.findAll();
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,list);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }








}
