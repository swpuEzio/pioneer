package com.yiling.pioneer.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @Author: xc
 * @Date: 2019/9/7 20:06
 * @Description:
 **/
@Service
public interface CompetitionService {
    /**
     * 获得未到期的比赛信息
     * @return
     */
    JSONObject getAllCompetition(int row,int pageNum);

    /**
     * 根据比赛ID获得比赛信息
     * @param cID
     * @return
     */
    JSONObject getCompetitionInfoByCID(String cID);
}
