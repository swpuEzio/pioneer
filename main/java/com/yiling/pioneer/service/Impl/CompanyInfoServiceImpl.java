package com.yiling.pioneer.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.yiling.pioneer.mapper.CompanyInfoMapper;
import com.yiling.pioneer.model.CompanyInfo;
import com.yiling.pioneer.service.CompanyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: xc
 * @Date: 2019/9/2 17:19
 * @Description:
 **/
@Service
public class CompanyInfoServiceImpl implements CompanyInfoService {

    @Autowired
    CompanyInfoMapper companyInfoMapper;
    @Override
    public JSONObject getInfo() {
        CompanyInfo companyInfo = companyInfoMapper.getInfo();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("purpose",companyInfo.getPurpose());
        jsonObject.put("idea",companyInfo.getIdea());
        jsonObject.put("goal",companyInfo.getGoal());
        jsonObject.put("resources",companyInfo.getResources());
        jsonObject.put("strategy",companyInfo.getStrategy());
        jsonObject.put("slogan",companyInfo.getSlogan());
        return jsonObject;

    }

    @Override
    public JSONObject updateInfo(CompanyInfo companyInfo) {
        JSONObject jsonObject = new JSONObject();
        if (companyInfoMapper.updateInfo(companyInfo)){
            jsonObject.put("status",200);
        }else {
            jsonObject.put("status",500);
        }
        return jsonObject;
    }
}
