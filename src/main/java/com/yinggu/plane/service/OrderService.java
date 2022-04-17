package com.yinggu.plane.service;

import com.yinggu.plane.pojo.Order;

import java.util.List;

public interface OrderService {

    int addOrder(Order order);

    List<Order> selectListById(Integer user_id);

}
