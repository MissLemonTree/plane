package com.yinggu.plane.service.impl;

import com.yinggu.plane.mapper.UserMapper;
import com.yinggu.plane.pojo.User;
import com.yinggu.plane.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectUser(String nickname) {
        return userMapper.selectUser(nickname);
    }

    @Override
    public User selectByPhoneNum(String phoneNum) {
        return userMapper.selectByPhoneNum(phoneNum);
    }
}
