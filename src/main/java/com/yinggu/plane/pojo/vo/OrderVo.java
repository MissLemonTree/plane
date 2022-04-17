package com.yinggu.plane.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName OrderVo
 * @Description TODO
 * @Author wangyichen
 * Date 2022/4/7 9:45
 * Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderVo {
    private int id;
    private String username;
    private String idNumber;
    private String phoneNum;
    private String flightNum;
    private String origin;
    private String destination;
    private String startTime;
    private String arrivalTime;
    private int price;
}
