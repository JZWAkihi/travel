package com.jiang.travels.controller;


import com.jiang.travels.entity.Place;
import com.jiang.travels.entity.Result;
import com.jiang.travels.service.PlaceServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.beans.IntrospectionException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequestMapping("place")
@RestController
@CrossOrigin
@Slf4j
public class PlaceController {

    @Autowired
    private PlaceServiceImpl placeService;

    @Value("${upload.dir}")
    private String realPath;


    @PostMapping("update")
    public Result update(MultipartFile pic,Place place) throws IOException {
        Result result = new Result();
        try {
            //对接受的文件做base64
            String picpath = Base64Utils.encodeToString(pic.getBytes());

            place.setPicpath(picpath);
            String extension = FilenameUtils.getExtension(pic.getOriginalFilename());
            String newFilename = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + extension;

            pic.transferTo(new File(realPath,newFilename));
            placeService.update(place);
            result.setMsg("修改景点信息成功");
        }catch (Exception e) {
            e.printStackTrace();
            result.setState(false).setMsg("修改景点信息失败");
        }

        return result;
    }




    @GetMapping("findOne")
    public Place findOne(String id){
        return placeService.findOne(id);
    }




    @GetMapping("delete")
    public Result delete(String id){
        Result result = new Result();
        try {

            placeService.delete(id);
            result.setMsg("删除景点成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setState(false).setMsg("删除景点错误");
        }

        return  result;
    }

    

    @PostMapping("save")
    public Result save(MultipartFile pic, Place place) throws IOException {
        Result result = new Result();
        try {
            // 文件上传
            String extension = FilenameUtils.getExtension(pic.getOriginalFilename());
            log.info(extension);
            String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "." + extension;
            log.info(newFileName);
            // base64编码处理(注意, 这一步必须放在 transferTo 操作前面!)
            place.setPicpath(Base64Utils.encodeToString(pic.getBytes()));
            // 文件上传
            File file = new File(realPath);
            pic.transferTo(new File(file, newFileName));
            // 保存place对象
            placeService.save(place);
            result.setMsg("保存景点信息成功!!!");
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(false).setMsg(e.getMessage());
        }
        return result;
    }

    /**
     * 根据省份id查询景点的方法
     */
    @GetMapping("findAllPlace")
    public Map<String ,Object> findAllPlace(Integer page, Integer rows,String provinceId){
        HashMap<String, Object> map = new HashMap<>();

        page = page == null ? 1 : page;
        rows = rows == null ? 4 : page;

        List<Place> placeList = placeService.findByProvinceIdPage(page, rows, provinceId);

        Integer counts = placeService.findByProvinceIdCounts(provinceId);

        Integer totalPages = counts%rows == 0 ? counts/rows : counts / rows + 1;

        map.put("places",placeList);
        map.put("page",page);
        map.put("counts",counts);
        map.put("totalPage",totalPages);
        return map;

    }


}
