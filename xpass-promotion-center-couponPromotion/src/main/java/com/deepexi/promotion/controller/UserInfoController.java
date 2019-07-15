package com.deepexi.promotion.controller;

import com.deepexi.util.config.Payload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhoust
 * @date 2019/6/10
 **/
@RestController
@RequestMapping("/api/v1/comment/userInfo")
public class UserInfoController {



    @GetMapping
    public Payload getUserInfo(){
        Map map = new HashMap<>();
        map.put("userName","gw_user_super_001");
        map.put("role","SUPER_ADMIN");
        return new Payload(map);
    }
}
