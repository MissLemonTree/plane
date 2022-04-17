package com.yinggu.plane.mapper;

import com.yinggu.plane.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {

    Integer addOrder(Order order);

    List<Order> selectListById(Integer user_id);
}
