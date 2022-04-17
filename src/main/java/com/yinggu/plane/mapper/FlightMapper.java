package com.yinggu.plane.mapper;

import com.yinggu.plane.pojo.Flight;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FlightMapper {

    List<Flight> selectList(Flight flight);

    Flight selectById(Integer id);

    Integer updateById(Flight flight);

}
