package com.jiang.travels.service;

import com.jiang.travels.dao.UserDao;
import com.jiang.travels.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public void register(User user) {

        if(userDao.findByUserName(user.getUsername()) == null){
            userDao.save(user);
        }else{
            throw new RuntimeException("用户名存在");
        }


    }
}
