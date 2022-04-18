package com.yinggu.plane.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinggu.plane.common.Result;
import com.yinggu.plane.pojo.Flight;
import com.yinggu.plane.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;

import java.util.List;



@Controller
@RequestMapping("/flight")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @RequestMapping("/flightList")
    @ResponseBody
    public Result flightList(@RequestParam(defaultValue = "1") Integer pageNum,Flight flight) throws ParseException {
        if(flight.getOrigin().equals("")){
            flight.setOrigin(null);
        }
        if(flight.getDestination().equals("")){
            flight.setDestination(null);
        }
        if(flight.getStartTime().equals("")){
            flight.setStartTime(null);
        }

        PageHelper.startPage(pageNum,5);
        List<Flight> flightList = flightService.selectList(flight);
        PageInfo<Flight> pageInfo = new PageInfo<>(flightList);
        return Result.success(pageInfo);
    }

    @RequestMapping("/toBook")
    public String toBook(int id, Model model){
        Flight flight = flightService.selectById(id);
        model.addAttribute("flight", flight);
        return "information";
    }

}
