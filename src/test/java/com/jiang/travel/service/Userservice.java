package com.jiang.travel.service;

import com.jiang.travels.TravelApplication;
import com.jiang.travels.dao.UserDao;
import com.jiang.travels.entity.User;
import com.jiang.travels.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = TravelApplication.class)
public class Userservice {
    @Autowired
    private UserService userService;
    @Test
    public void register(){
        User user = new User();
        user.setUsername("xiaoxiao");
        user.setUsername("jiangzhiwei");
        user.setEmail("jiang@qq.com");
        userService.register(user);
    }


    @Test
    public void findUser(){
        User user = new User();
        user.setPassword("123456");
        user.setUsername("admin");
        User byUser = userService.findByUser(user);
        System.out.println(byUser);

    }


}
