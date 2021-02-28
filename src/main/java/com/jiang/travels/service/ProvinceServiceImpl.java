package com.jiang.travels.service;

import com.jiang.travels.dao.ProvinceDAO;
import com.jiang.travels.entity.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceServiceImpl implements ProinceService {

    @Autowired
    private ProvinceDAO proinceDAO;


    @Override
    public List<Province> findByPage(Integer Page, Integer rows) {
        return proinceDAO.findByPage(Page,rows);
    }

    @Override
    public Integer findTotal() {
        return proinceDAO.findTotals();
    }
}
