package com.jiang.travels.service;

import com.jiang.travels.entity.Place;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PlaceService {
    /**
     *根据省份id查询所有景点信息并排序
     * @param start
     * @param rows
     * @param provinceId
     * @return
     */
    List<Place> findByProvinceIdPage(Integer start,Integer rows,String provinceId);

    /**
     * 根据省份id查询当前省份所有景点个数
     * @param provinceId
     * @return
     */
    Integer findByProvinceIdCounts(String provinceId);

    void save(Place place);

    void delete(String id);

    Place findOne(String id);

    void update(Place place);


}
