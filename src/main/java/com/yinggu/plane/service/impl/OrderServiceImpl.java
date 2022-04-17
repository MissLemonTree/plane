package com.yinggu.plane.service.impl;

import com.yinggu.plane.mapper.FlightMapper;
import com.yinggu.plane.mapper.OrderMapper;
import com.yinggu.plane.pojo.Flight;
import com.yinggu.plane.pojo.Order;
import com.yinggu.plane.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName OrderServiceImpl
 * @Description TODO
 * @Author wangyichen
 * Date 2022/4/6 19:09
 * Version 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public int addOrder(Order order) {
        return orderMapper.addOrder(order);
    }

    @Override
    public List<Order> selectListById(Integer user_id) {
        return orderMapper.selectListById(user_id);
    }


}
