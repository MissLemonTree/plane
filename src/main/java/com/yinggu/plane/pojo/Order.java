package com.yinggu.plane.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private int id;

    private int flight_id;

    private int user_id;

    private String name;

    private String idNumber;

    private String phoneNum;

}
