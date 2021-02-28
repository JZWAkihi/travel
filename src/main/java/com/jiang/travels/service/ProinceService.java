package com.jiang.travels.service;

import com.jiang.travels.entity.Province;

import java.util.List;

public interface ProinceService {

    //分页查询
    List<Province> findByPage(Integer Page,Integer rows);

    //查询总页数
    Integer findTotal();

}
