package com.yinggu.plane.mapper;

import com.yinggu.plane.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User selectUser(String nickname);

    User selectByPhoneNum(String phoneNum);

}
