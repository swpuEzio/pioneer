package com.yiling.pioneer.controller;

import com.alibaba.fastjson.JSONObject;
import com.yiling.pioneer.service.GameRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @Author: xc
 * @Date: 2019/9/11 18:56
 * @Description:
 **/
@RestController
public class GameRecordController {
    @Autowired
    GameRecordService gameRecordService;
    @GetMapping("/getRecordByuID")
    public JSONObject getRecordByuID(@AuthenticationPrincipal Principal principal){
        String username = principal.getName();
        return gameRecordService.getRecordByuID(username);
    }

    @GetMapping("/getRecordBycID")
    public JSONObject getRecordBycID(@RequestParam("cID") String cID){
        return gameRecordService.getRecordBycID(cID);
    }
}
