package com.jiang.travels.service;

import com.jiang.travels.entity.User;

public interface UserService {
    void register(User user);

    User findByUser(User user);

    //修改
    void updata(User user);
}

