package com.yinggu.plane.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName User
 * @Description TODO
 * @Author wangyichen
 * Date 2022/4/6 16:40
 * Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int id;

    private String nickname;

    private String username;

    private String password;

    private String sex;

    private String phoneNum;


}
