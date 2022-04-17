package com.yinggu.plane.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName Flight
 * @Description TODO
 * @Author wangyichen
 * Date 2022/4/6 16:40
 * Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight {

    private int id;

    private String flightNum;

    private String origin;

    /** 目的地 */
    private String destination;
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String startTime;

    private String arrivalTime;

    private int amount;

    private int price;
}
