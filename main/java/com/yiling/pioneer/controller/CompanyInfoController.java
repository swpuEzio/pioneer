package com.yiling.pioneer.controller;

import com.alibaba.fastjson.JSONObject;
import com.yiling.pioneer.model.CompanyInfo;
import com.yiling.pioneer.service.CompanyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xc
 * @Date: 2019/9/2 19:25
 * @Description:
 **/
@CrossOrigin
@RestController
public class CompanyInfoController {

    @Autowired
    CompanyInfoService companyInfoService;

    @GetMapping("/getCompanyInfo")
    public JSONObject getCompanyInfo(){
        return companyInfoService.getInfo();
    }
    @PostMapping("/updateCompanyInfo")
    public JSONObject updateCompanyInfo(CompanyInfo companyInfo){
        return companyInfoService.updateInfo(companyInfo);
    }
}
