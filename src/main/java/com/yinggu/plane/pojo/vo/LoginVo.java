package com.yinggu.plane.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName LoginVo
 * @Description TODO
 * @Author wangyichen
 * Date 2022/4/6 19:17
 * Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginVo {

    private String nickname;

    private String password;
}
