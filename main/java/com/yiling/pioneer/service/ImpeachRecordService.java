package com.yiling.pioneer.service;

import com.alibaba.fastjson.JSONObject;
import com.yiling.pioneer.model.ImpeachRecord;
import org.springframework.stereotype.Service;

/**
 * @Author: xc
 * @Date: 2019/9/5 17:16
 * @Description:
 **/
@Service
public interface ImpeachRecordService {
    /**
     * 新增举报记录
     * @param impeachRecord
     * @return
     */
    JSONObject addRecord(ImpeachRecord impeachRecord);
}
