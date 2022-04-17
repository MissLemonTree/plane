package com.yinggu.plane.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinggu.plane.common.Result;
import com.yinggu.plane.pojo.Flight;
import com.yinggu.plane.pojo.Order;
import com.yinggu.plane.pojo.User;
import com.yinggu.plane.pojo.vo.OrderVo;
import com.yinggu.plane.service.FlightService;
import com.yinggu.plane.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName OrderController
 * @Description TODO
 * @Author wangyichen
 * Date 2022/4/6 19:10
 * Version 1.0
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private FlightService flightService;

    public String toOrder(){
        return null;
    }

    @RequestMapping("/addOrder")
    @ResponseBody
    public Result addOrder(Order order,HttpSession session){
        User user = (User) session.getAttribute("user");
        order.setUser_id(user.getId());
        int i = orderService.addOrder(order);
        if(i==1){
            Flight flight = flightService.selectById(order.getFlight_id());
            flight.setAmount(flight.getAmount()-1);
            flightService.updateById(flight);
            return Result.success(i);
        }else {
            return Result.fail("购票失败");
        }
    }

    @RequestMapping("/orderList")
    public String toOrderList(){
        return "order";
    }

    @RequestMapping("/getData")
    @ResponseBody
    public Result getOrderList(@RequestParam(defaultValue = "1") Integer pageNum, HttpSession session){
        User user = (User) session.getAttribute("user");
        PageHelper.startPage(pageNum, 5);
        List<Order> orderList = orderService.selectListById(user.getId());
        List<OrderVo> orderVoList = new ArrayList<>();
        for(Order order:orderList){
            Flight flight = flightService.selectById(order.getFlight_id());
            OrderVo orderVo = new OrderVo();
            orderVo.setUsername(order.getName());
            orderVo.setIdNumber(order.getIdNumber());
            orderVo.setPhoneNum(order.getPhoneNum());
            orderVo.setFlightNum(flight.getFlightNum());
            orderVo.setOrigin(flight.getOrigin());
            orderVo.setDestination(flight.getDestination());
            orderVo.setStartTime(flight.getStartTime());
            orderVo.setArrivalTime(flight.getArrivalTime());
            orderVo.setPrice(flight.getPrice());
            orderVoList.add(orderVo);
        }
        PageInfo<OrderVo> pageInfo = new PageInfo<>(orderVoList);
        return Result.success(pageInfo);
    }

}
