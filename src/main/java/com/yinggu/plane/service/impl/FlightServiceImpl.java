package com.yinggu.plane.service.impl;

import com.yinggu.plane.mapper.FlightMapper;
import com.yinggu.plane.pojo.Flight;
import com.yinggu.plane.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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
