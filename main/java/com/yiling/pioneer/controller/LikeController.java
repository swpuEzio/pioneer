package com.yiling.pioneer.controller;

import com.alibaba.fastjson.JSONObject;
import com.yiling.pioneer.model.ArticleLike;
import com.yiling.pioneer.model.MyUser;
import com.yiling.pioneer.service.ArticleLikeService;
import com.yiling.pioneer.service.MyUserService;
import com.yiling.pioneer.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @Author: xc
 * @Date: 2019/8/30 13:04
 * @Description:
 **/
@RestController
public class LikeController {

    @Autowired
    MyUserService myUserService;
    @Autowired
    RedisService redisService;
    @GetMapping("/like")
    public JSONObject like(@RequestParam("articleID") String articleID,
                           @AuthenticationPrincipal Principal principal){
        String username = principal.getName();
//        String username = "15680972017";
        MyUser currentUser = myUserService.getUserAllInfoByUserName(username);
        redisService.saveLiked2Redis(String.valueOf(currentUser.getUID()),articleID);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status",200);
        return jsonObject;
    }
}
