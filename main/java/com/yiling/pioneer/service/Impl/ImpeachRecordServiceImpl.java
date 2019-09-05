package com.yiling.pioneer.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.yiling.pioneer.mapper.ImpeachRecordMapper;
import com.yiling.pioneer.model.ImpeachRecord;
import com.yiling.pioneer.service.ImpeachRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: xc
 * @Date: 2019/9/5 17:17
 * @Description:
 **/
@Service
public class ImpeachRecordServiceImpl implements ImpeachRecordService {

    @Autowired
    ImpeachRecordMapper impeachRecordMapper;
    @Override
    public JSONObject addRecord(ImpeachRecord impeachRecord) {
        JSONObject jsonObject = new JSONObject();
        if (impeachRecordMapper.addRecord(impeachRecord)){
            jsonObject.put("status",200);
        }else {
            jsonObject.put("status",500);
        }
        return jsonObject;
    }
}
