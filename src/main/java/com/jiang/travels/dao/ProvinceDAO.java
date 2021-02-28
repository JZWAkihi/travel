package com.jiang.travels.dao;

import com.jiang.travels.entity.Province;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface ProvinceDAO extends BaseDAO<Province,String> {
}
