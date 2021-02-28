package com.jiang.travels.service;

import com.jiang.travels.entity.Province;

import java.util.List;

public interface ProinceService {

    //分页查询
    List<Province> findByPage(Integer Page,Integer rows);

    //查询总页数
    Integer findTotal();

    //添加省份
    void save(Province province);

    //删除省份
    void delete(String id);

    //查找一个
    Province findOne(String id);

    //修改省份信息
    void updata(Province province);

}
