package com.jiang.travels.dao;

import com.jiang.travels.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserDao {

    //根据用户查询用户
    User findByUserName(String username);

    //注册用户
    void save(User user);
}
