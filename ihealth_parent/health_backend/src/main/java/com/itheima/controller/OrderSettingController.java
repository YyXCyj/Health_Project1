package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.OrdeSettingService;
import com.itheima.utils.POIUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author yy
 * @version 1.0
 */
@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {
    @Reference
    private OrdeSettingService orderSettingService;

    @RequestMapping("/upload")
    public Result upload(@RequestParam("excelFile") MultipartFile excelFile){
        try {
            List<String[]> list = POIUtils.readExcel(excelFile);
            List<OrderSetting> arrayList = new ArrayList<>();
            for (String[] strings : list) {
                String orderData = strings[0];
                String number = strings[1];
                OrderSetting orderSetting = new OrderSetting(new Date(orderData), Integer.parseInt(number));
                arrayList.add(orderSetting);
            }
            orderSettingService.add(arrayList);
            return new Result(true,MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
    }
//    @RequestMapping("/getOrderSettingByMonth")
//    public Result getOrderSettingByMonth(String data){
//        try{
//            List<Map> list=orderSettingService.getOrderSettingByMonth(data);
//            return new Result(true,MessageConstant.GET_ORDERSETTING_SUCCESS,list);
//        }catch (Exception e){
//            e.printStackTrace();
//            return new Result(false,MessageConstant.GET_ORDERSETTING_FAIL);
//        }
//
//
//    }
        @RequestMapping("/getOrderSettingByMonth")
        public Result getOrderSettingByMonth(String date){//参数格式为：2019-03
            try{
                List<Map> list = orderSettingService.getOrderSettingByMonth(date);
                //获取预约设置数据成功
                return new Result(true,MessageConstant.GET_ORDERSETTING_SUCCESS,list);
            }catch (Exception e){
                e.printStackTrace();
                //获取预约设置数据失败
                return new Result(false,MessageConstant.GET_ORDERSETTING_FAIL);
            }
        }
        @RequestMapping("/editNumberByDate")
        public Result editNumberByDate(@RequestBody OrderSetting orderSetting){
            try{
                orderSettingService.editNumberByDate(orderSetting);
                //获取预约设置数据成功
                return new Result(true,MessageConstant.ORDERSETTING_SUCCESS);
            }catch (Exception e){
                e.printStackTrace();
                //获取预约设置数据失败
                return new Result(false,MessageConstant.ORDERSETTING_FAIL);
            }

        }
}
