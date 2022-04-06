package com.itheima.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetmealService;
import com.itheima.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.UUID;

/**
 * @author yy
 * @version 1.0
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Autowired
    private JedisPool jedisPool;
    @Reference
    private SetmealService setmealService;

    @RequestMapping("/upload")
    public Result upload(@RequestParam("imgFile") MultipartFile imgFile){
//        System.out.println(imgFile);
        String originalFilename = imgFile.getOriginalFilename();
        int i = originalFilename.lastIndexOf('.');
        String substring = originalFilename.substring(i - 1);
        String filename= UUID.randomUUID().toString()+substring;
        try {
            QiniuUtils.upload2Qiniu(imgFile.getBytes(),filename);
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,filename);

        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
        return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS,filename);
    }

    @RequestMapping("/add")
    public Result add(Integer[] checkgroupIds,@RequestBody Setmeal setmeal){
        try{
            setmealService.add(setmeal,checkgroupIds);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_SETMEAL_FAIL);
        }
        return new Result(true,MessageConstant.ADD_SETMEAL_SUCCESS);
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = setmealService.pageQuery(queryPageBean);
        return pageResult;
    }
}
