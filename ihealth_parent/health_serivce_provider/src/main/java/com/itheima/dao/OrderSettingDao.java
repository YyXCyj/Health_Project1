package com.itheima.dao;

import com.itheima.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author yy
 * @version 1.0
 */
public interface OrderSettingDao {
    public void add(OrderSetting orderSetting);
    public void editNumberByOrderDate(OrderSetting orderSetting);
    public long findCountByOrderDate(Date orderDate);
    public List<OrderSetting> getOrderSettingByMonth(Map date);
//    public void editNumberByDate(OrderSetting orderSetting);
   public OrderSetting findByOrderDate(Date date);
    //更新已预约人数
    public void editReservationsByOrderDate(OrderSetting orderSetting);
}
