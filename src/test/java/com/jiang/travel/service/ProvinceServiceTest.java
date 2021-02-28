package com.jiang.travel.service;

import com.jiang.travels.TravelApplication;
import com.jiang.travels.entity.Province;
import com.jiang.travels.service.ProvinceServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = TravelApplication.class)
public class ProvinceServiceTest {

    @Autowired
    private ProvinceServiceImpl provinceService;

    @Test
    public void findByPage(){
        List<Province> list = provinceService.findByPage(0,5);

        list.forEach(pr->{
            System.out.println(pr);
        });
    }


    @Test
    public void findTotal(){
        System.out.println(provinceService.findTotal());
    }


}
