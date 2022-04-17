package com.yinggu.plane.service.impl;

import com.yinggu.plane.mapper.FlightMapper;
import com.yinggu.plane.pojo.Flight;
import com.yinggu.plane.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName FlightServiceImpl
 * @Description TODO
 * @Author wangyichen
 * Date 2022/4/6 19:09
 * Version 1.0
 */
@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightMapper flightMapper;

    @Override
    public List<Flight> selectList(Flight flight) {
        return flightMapper.selectList(flight);
    }

    @Override
    public Flight selectById(Integer id) {
        return flightMapper.selectById(id);
    }

    @Override
    public Integer updateById(Flight flight) {
        return flightMapper.updateById(flight);
    }
}
