package com.yinggu.plane.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight {

    private int id;

    private String flightNum;

    private String origin;

    /** 目的地 */
    private String destination;

    private String startTime;

    private String arrivalTime;

    private int amount;

    private int price;
}
