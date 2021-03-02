package com.jiang.travels.service;

import com.jiang.travels.dao.PlaceDAO;
import com.jiang.travels.dao.ProvinceDAO;
import com.jiang.travels.entity.Place;
import com.jiang.travels.entity.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {


    @Autowired
    private PlaceDAO placeDAO;

    @Autowired
    private ProvinceServiceImpl provinceService;

    @Autowired
    private ProvinceDAO provinceDAO;


    @Override
    public List<Place> findByProvinceIdPage(Integer page, Integer rows, String provinceId) {
        int start = page - 1;
        return placeDAO.findByProvinceIdPage(start*rows, rows, provinceId);
    }

    @Override
    public void delete(String id) {
        // 不能直接删除景点, 要先让省份中的景点个数 -1, 再删除景点
        Place place = placeDAO.findOne(id);
        Province province = provinceDAO.findOne(place.getProvinceid());
        province.setPlacecounts(province.getPlacecounts() - 1); // 让省份的景点个数 -1
        provinceDAO.update(province);
        // 删除景点信息
        placeDAO.delete(id);

    }

    @Override
    public void update(Place place) {
        placeDAO.update(place);
    }

    @Override
    public Place findOne(String id) {
        return placeDAO.findOne(id);
    }

    @Override
    public void save(Place place) {
        placeDAO.save(place);
        Province province = provinceService.findOne(place.getId());
        province.setPlacecounts(province.getPlacecounts() + 1);
    }

    @Override
    public Integer findByProvinceIdCounts(String provinceId) {
        return placeDAO.findByProvinceIdCounts(provinceId);
    }
}
