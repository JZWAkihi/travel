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
        List<Province> list = provinceService.findByPage(3,4);

        list.forEach(pr->{
            System.out.println(pr);
        });
    }


    @Test
    public void findTotal(){
        System.out.println(provinceService.findTotal());
    }


    @Test
    public void saveTest(){
        Province province = new Province();

        province.setName("开州区");
        province.setPlacecounts(20);
        province.setTags("蒋志伟");

        provinceService.save(province);
    }


    @Test
    public void delete(){
        provinceService.delete("10");
    }



    @Test
    public void findoneTest(){
        System.out.println(provinceService.findOne("1"));
    }

    @Test
    public void updataTest(){
        Province province = new Province();
        province.setId("1");
        province.setName("北京");
        province.setTags("天安门、圆明园");
        province.setPlacecounts(20);
        provinceService.updata(province);


        System.out.println(provinceService.findOne("1"));

    }

}
