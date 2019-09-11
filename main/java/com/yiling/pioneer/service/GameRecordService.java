package com.yiling.pioneer.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @Author: xc
 * @Date: 2019/9/11 15:15
 * @Description:
 **/
@Service
public interface GameRecordService {
    /**
     * 获得用户参加的未过期比赛
     * @param uID
     * @return
     */
    JSONObject getRecordByuID(String uID);

    /**
     * 获得目标比赛参加的用户
     * @param cID
     * @return
     */
    JSONObject getRecordBycID(String cID);
}
