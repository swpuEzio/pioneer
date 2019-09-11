package com.yiling.pioneer.controller;

import com.alibaba.fastjson.JSONObject;
import com.yiling.pioneer.model.MyUser;
import com.yiling.pioneer.service.CompetitionService;
import com.yiling.pioneer.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @Author: xc
 * @Date: 2019/9/8 15:10
 * @Description:
 **/
@RestController
public class CompetitionController {

    @Autowired
    CompetitionService competitionService;
    @Autowired
    MyUserService myUserService;
    @GetMapping("/getAllUnexpiredCompetition")
    public JSONObject getAllUnexpiredCompetition(@RequestParam("row") String row, @RequestParam("pageNum") String pageNum){
        return competitionService.getAllCompetition(Integer.parseInt(row),Integer.parseInt(pageNum));
    }
    @GetMapping("/getCompetitionInfoByCID")
    public JSONObject getCompetitionInfoByCID(@AuthenticationPrincipal Principal principal){
        String username = principal.getName();
        MyUser current = myUserService.getUserAllInfoByUserName(username);
        return competitionService.getCompetitionInfoByCID(String.valueOf(current.getUID()));
    }
}
