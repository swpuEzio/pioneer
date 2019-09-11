package com.yiling.pioneer.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.yiling.pioneer.mapper.GameRecordMapper;
import com.yiling.pioneer.model.GameRecord;
import com.yiling.pioneer.service.GameRecordService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: xc
 * @Date: 2019/9/11 15:29
 * @Description:
 **/
public class GameRecordServiceImpl implements GameRecordService {
    @Autowired
    GameRecordMapper gameRecordMapper;
    @Override
    public JSONObject getRecordByuID(String uID) {
        JSONObject jsonObject = new JSONObject();
        List<GameRecord> gameRecords = gameRecordMapper.getRecordByuID(uID);
        if (gameRecords.isEmpty()){
            jsonObject.put("status",404);
        }
        JSONArray result = new JSONArray();
        for(GameRecord gameRecord:gameRecords){
            JSONObject object = new JSONObject();
            object.put("cID",gameRecord.getCID());
            result.add(object);
        }
        jsonObject.put("status",200);
        jsonObject.put("result",result);
        return jsonObject;
    }

    @Override
    public JSONObject getRecordBycID(String cID) {
        JSONObject jsonObject = new JSONObject();
        List<GameRecord> gameRecords = gameRecordMapper.getRecordBycID(cID);
        if (gameRecords.isEmpty()){
            jsonObject.put("status",404);
        }
        JSONArray result = new JSONArray();
        for(GameRecord gameRecord:gameRecords){
            JSONObject object = new JSONObject();
            object.put("uID",gameRecord.getUID());
            result.add(object);
        }
        jsonObject.put("status",200);
        jsonObject.put("result",result);
        return jsonObject;
    }
}
