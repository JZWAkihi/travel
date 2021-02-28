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

        int start = Page - 1;
        return proinceDAO.findByPage(start*rows,rows);
    }

    @Override
    public void save(Province province) {
        proinceDAO.save(province);
    }

    @Override
    public Province findOne(String id) {
        return proinceDAO.findOne(id);
    }

    @Override
    public void updata(Province province) {
        proinceDAO.update(province);
    }

    @Override
    public void delete(String id) {
        proinceDAO.delete(id);
    }

    @Override
    public Integer findTotal() {
        return proinceDAO.findTotals();



    }
}
