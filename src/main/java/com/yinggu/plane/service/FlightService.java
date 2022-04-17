package com.yinggu.plane.service;

import com.yinggu.plane.pojo.Flight;


import java.util.List;

public interface FlightService {

    List<Flight> selectList(Flight flight);

    Flight selectById(Integer id);

    Integer updateById(Flight flight);

}
