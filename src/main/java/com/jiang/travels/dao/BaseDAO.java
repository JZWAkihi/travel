package com.jiang.travels.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;


/***
 *
 * @param <T>  实体类
 * @param <K>  主键类型
 */
public interface BaseDAO<T,K> {
    void save(T t);

    void update(T t);

    void delete(K k);

    List<T> findAll();

    T findOne(K k);

    List<T> findByPage(@Param("start") Integer start,@Param("rows") Integer rows);

    Integer findTotals();



}
