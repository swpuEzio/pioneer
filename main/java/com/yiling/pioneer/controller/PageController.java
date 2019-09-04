package com.yiling.pioneer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: xc
 * @Date: 2019/7/10 10:22
 * @Description:
 **/
@Controller
public class PageController {

    @GetMapping("/login")
    public String login(){
        return "/login";
    }
}
