package com.jiang.travels.controller;


import com.jiang.travels.entity.Province;
import com.jiang.travels.service.ProvinceServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/province")
@Slf4j
public class ProvinceController {

    @Autowired
    private ProvinceServiceImpl provinceService;

    @GetMapping("findByPage")
    public Map<String,Object> findByPages(Integer page,Integer row){
        page = page == null ? 0 : page;
        row = row == null ? 4 : row;
        HashMap<String,Object> hashMap = new HashMap<>();

        List<Province> provinces = provinceService.findByPage(page, row);

        Integer totals = provinceService.findTotal();

        Integer totalPage = totals%row == 0 ? totals/row : totals/row + 1;

        hashMap.put("province",provinces);
        hashMap.put("totals",totals);
        hashMap.put("totalPage",totalPage);
        hashMap.put("page",page);
        log.info(hashMap.toString());

        return hashMap;
    }

}
