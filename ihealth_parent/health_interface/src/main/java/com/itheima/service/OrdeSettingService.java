package com.itheima.service;

import com.itheima.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

/**
 * @author yy
 * @version 1.0
 */
public interface OrdeSettingService {
    public void add(List<OrderSetting> data);
//    public List<Map> getOrderSettingByMonth(String data);
public List<Map> getOrderSettingByMonth(String date);//参数格式为：2019-03
    public void editNumberByDate(OrderSetting orderSetting);
}
