package com.yiling.pioneer.service;

import com.alibaba.fastjson.JSONObject;
import com.yiling.pioneer.model.CompanyInfo;
import org.springframework.stereotype.Service;

/**
 * @Author: xc
 * @Date: 2019/9/2 17:18
 * @Description:
 **/
@Service
public interface CompanyInfoService {
    /**
     * 获得公司信息
     * @return
     */
    JSONObject getInfo();

    /**
     * 更新公司信息
     * @param companyInfo
     * @return
     */
    JSONObject updateInfo(CompanyInfo companyInfo);
}
