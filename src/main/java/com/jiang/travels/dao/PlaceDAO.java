package com.jiang.travels.dao;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.jiang.travels.entity.Place;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Mapper
public interface PlaceDAO extends BaseDAO<Place,String> {

    List<Place>  findByProvinceIdPage(@Param("start") Integer start,@Param("rows") Integer rows,@Param("provinceId") String provinceId);

    Integer findByProvinceIdCounts(String provinceId);

}
