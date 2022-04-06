package com.itheima.controller;

import com.aliyuncs.exceptions.ClientException;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisMessageConstant;
import com.itheima.entity.Result;
import com.itheima.utils.SMSUtils;
import com.itheima.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

/**
 * @author yy
 * @version 1.0
 */
@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {
    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("send4Order")
    public Result send4Order(String telephone){
        Integer code = 1234;
        //将生成的验证码缓存到redis
        jedisPool.getResource().setex(
                telephone + RedisMessageConstant.SENDTYPE_ORDER,5 * 60,code.toString());
        //验证码发送成功
        return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }

    @RequestMapping("/send4Login")
    public Result send4Login(String telephone){
        Integer code = 123456;//生成6位数字验证码
//        try {
//            //发送短信
//            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE,telephone,code.toString());
//        } catch (ClientException e) {
//            e.printStackTrace();
//            //验证码发送失败
//            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
//        }
        System.out.println("发送的手机验证码为：" + code);
        //将生成的验证码缓存到redis
        jedisPool.getResource().setex(telephone+RedisMessageConstant.SENDTYPE_LOGIN,
                5 * 60,
                code.toString());
        //验证码发送成功
        return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }
}
