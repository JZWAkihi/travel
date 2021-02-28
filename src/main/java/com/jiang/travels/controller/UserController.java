package com.jiang.travels.controller;


import com.jiang.travels.entity.Result;
import com.jiang.travels.entity.User;
import com.jiang.travels.service.UserService;
import com.jiang.travels.utils.CreateImageCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.SimpleFormatter;

@RestController
@RequestMapping("user")
@CrossOrigin   //允许跨域
@Slf4j // 日志对象
public class UserController {


    @Autowired
    private UserService userService;



    @RequestMapping(value = "/login")
    public Result login(@RequestBody User user,HttpServletRequest request){
        Result result = new Result();

        log.info(user.toString());

        try {
            User UserDB = userService.findByUser(user);
            //登录成功后保存用户的标签
            //ServletContext  application
            request.getServletContext().setAttribute(UserDB.getId(),UserDB);

            result.setMsg("登录成功").setUserId(UserDB.getId());
        }catch (Exception e){
            e.printStackTrace();
            result.setState(false).setMsg(e.getMessage());

            log.info(result.toString());
        }
        return result;
    }





    /**
     * 用户注册
     * @param code
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Result register(String code, String key, @RequestBody User user, HttpServletRequest request) { // axios发送的是JSON数据, 需要加@RequestBody来接收
        Result result = new Result();
        log.info("接收的验证码：" + code);
        log.info("接收的user对象：" + user);
        String keyCode = (String) request.getServletContext().getAttribute(key); // 获取验证码

        log.info("keyCode" + keyCode);
        try {
            if (code.equalsIgnoreCase(keyCode)) {
                userService.register(user);
                result.setMsg("注册成功");
            }else{
                throw new RuntimeException("验证码错误!!!");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setMsg(e.getMessage()).setState(false);
        }
        return result;
    }

    @GetMapping("getImage")
    @ResponseBody
    public Map<String, String> getImage(HttpServletRequest request) throws IOException {
        Map<String, String> result = new HashMap<>();
        CreateImageCode createImageCode = new CreateImageCode();
        // 获取验证码
        String securityCode = createImageCode.getCode();
        // 验证码存入session
        String key = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        request.getServletContext().setAttribute(key, securityCode);
        log.info("------------------------" + securityCode);
        // 生成图片
        BufferedImage image = createImageCode.getBuffImg();
        //进行base64编码
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", bos);
        String string = Base64Utils.encodeToString(bos.toByteArray());
        result.put("key", key);
        result.put("image", string);
        return result;
    }
}
