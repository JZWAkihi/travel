package com.jiang.travels.service;

import com.jiang.travels.dao.UserDao;
import com.jiang.travels.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /***
     * 用户注册
     * @param user
     */
    @Override
    public void register(User user) {

        if(userDao.findByUserName(user.getUsername()) == null){
            userDao.save(user);
        }else{
            throw new RuntimeException("用户名存在");
        }
    }


    /**
     *
     * 用户登录
     * @param user
     * @return
     */
    @Override
    public User findByUser(User user) {
        User UserDB = userDao.findByUserName(user.getUsername());
        log.info(user.toString());
        log.info(UserDB + " ");
        if(UserDB!=null){
            if (UserDB.getPassword().equals(user.getPassword())){
                return UserDB;
            }else{
                throw new RuntimeException("密码错误");
            }
        }else{
            throw new RuntimeException("用户名错误");
        }
    }


    //修改

    @Override
    public void updata(User user) {
        userDao.updata(user);
    }
}
