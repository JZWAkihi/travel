package com.jiang.travel.service;

import com.jiang.travels.TravelApplication;
import com.jiang.travels.service.PlaceServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = TravelApplication.class)
public class PlaceServiceTest {

    @Autowired
    private PlaceServiceImpl placeService;


    @Test
    public void findByProvinceIdPageTest(){
        System.out.println(placeService.findByProvinceIdPage(1, 2, "1"));
    }


    @Test
    public void findByProvinceIdCountsTest(){
        System.out.println(placeService.findByProvinceIdCounts("1"));
    }
}
