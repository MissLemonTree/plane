package com.yinggu.plane.service;

import com.yinggu.plane.pojo.User;

public interface UserService {

    User selectUser(String nickname);

    User selectByPhoneNum(String phoneNum);

}
