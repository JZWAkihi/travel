package com.jiang.travels.controller;


import com.jiang.travels.entity.Province;
import com.jiang.travels.entity.Result;
import com.jiang.travels.service.ProvinceServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("update")
    public Result update(@RequestBody Province province){
        Result result = new Result();

        try{
            provinceService.updata(province);
            result.setMsg("修改省份信息成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setMsg("修改省份信息失败").setState(false);
        }

        return result;
    }

    @GetMapping("findOne")
    public Province findOne(String id){
        return provinceService.findOne(id);
    }




    @PostMapping("delete")
    public Result delete(String id){
        Result result = new Result();

        try{
            provinceService.delete(id);
            result.setMsg("删除省份成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setMsg("删除省份错误").setState(false);
        }

        return result;
    }



    @PostMapping("save")
    public Result save(@RequestBody Province province){
        Result result = new Result();
        try{
            provinceService.save(province);
            result.setMsg("保存省份成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setMsg("省份保存失败").setState(false);
        }


        return result;
    }





    @GetMapping("findByPage")
    public Map<String,Object> findByPages(Integer page,Integer rows){
        page = page == null ? 1 : page;
        rows = rows == null ? 4 : rows;
        HashMap<String,Object> hashMap = new HashMap<>();

        List<Province> provinces = provinceService.findByPage(page, rows);

        Integer totals = provinceService.findTotal();

        Integer totalPage = totals%rows == 0 ? totals/rows : totals/rows + 1;

        hashMap.put("provinces",provinces);
        hashMap.put("totals",totals);
        hashMap.put("totalPage",totalPage);
        hashMap.put("page",page);
        log.info(hashMap.toString());

        return hashMap;
    }

}
