package com.yinggu.plane.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Order
 * @Description TODO
 * @Author wangyichen
 * Date 2022/4/6 16:41
 * Version 1.0
 */
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
