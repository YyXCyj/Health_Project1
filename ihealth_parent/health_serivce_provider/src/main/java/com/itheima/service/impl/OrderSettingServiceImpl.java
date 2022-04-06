package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.OrderSettingDao;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.OrdeSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yy
 * @version 1.0
 */
@Service(interfaceClass = OrdeSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrdeSettingService {

    @Autowired
    private OrderSettingDao orderSettingDao;
    public void add(List<OrderSetting> data) {
       if(data !=null && data.size()>0){
           for (OrderSetting orderSetting : data) {
               long countByOrderDate = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
               if(countByOrderDate>0){
                   orderSettingDao.editNumberByOrderDate(orderSetting);
               }else {
                   orderSettingDao.add(orderSetting);
               }
           }
       }
    }


//    public List<Map> getOrderSettingByMonth(String data) {
//        String begin=data+"-1";
//        String end=data+"-31";
//        Map<String,String> map=new HashMap<>();
//        map.put("begin",begin);
//        map.put("end",end);
//        List<OrderSetting> list = orderSettingDao.getOrderSettingByMonth(map);
//        List<Map> res = new ArrayList<>();
//        if(list !=null && list.size()>0){
//            for (OrderSetting orderSetting : list) {
//                HashMap<String, Object> map1 = new HashMap<>();
//                map1.put("date",orderSetting.getOrderDate().getDate());
//                map1.put("number",orderSetting.getNumber());
//                map1.put("reservations",orderSetting.getReservations());
//                res.add(map1);
//            }
//        }
//        return res;
//    }
public List<Map> getOrderSettingByMonth(String date) {//2019-3
    String dateBegin = date + "-1";//2019-3-1
    String dateEnd = date + "-31";//2019-3-31
    Map map = new HashMap();
    map.put("dateBegin",dateBegin);
    map.put("dateEnd",dateEnd);
    List<OrderSetting> list = orderSettingDao.getOrderSettingByMonth(map);
    List<Map> data = new ArrayList<>();
    for (OrderSetting orderSetting : list) {
        Map orderSettingMap = new HashMap();
        orderSettingMap.put("date",orderSetting.getOrderDate().getDate());//获得日期（几号）
        orderSettingMap.put("number",orderSetting.getNumber());//可预约人数
        orderSettingMap.put("reservations",orderSetting.getReservations());//已预约人数
        data.add(orderSettingMap);
    }
    return data;
}


    public void editNumberByDate(OrderSetting orderSetting) {
        long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
        if(count > 0){
            //当前日期已经进行了预约设置，需要进行修改操作
            orderSettingDao.editNumberByOrderDate(orderSetting);
        }else{
            //当前日期没有进行预约设置，进行添加操作
            orderSettingDao.add(orderSetting);
        }
    }
}
















