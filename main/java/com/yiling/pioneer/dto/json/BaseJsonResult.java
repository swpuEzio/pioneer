package com.yiling.pioneer.dto.json;

import com.alibaba.fastjson.JSONObject;
import com.yiling.pioneer.utils.ToJsonUtil;

/**
 * @author Seagull_gby
 * @date 2019/6/1 17:15
 * Description: 基础JSON数据封装
 */

public class BaseJsonResult<T> extends JsonResult<T>{

    @Override
    public JSONObject success(T data) {
        code = 200;
        msg = "success";
        this.data = data;

        JSONObject json = ToJsonUtil.toJson(this);

        return json;
    }

    @Override
    public JSONObject fail(String msg) {
        code = 500;
        this.msg = msg;

        JSONObject json = ToJsonUtil.toJson(this);

        return json;
    }

    @Override
    public JSONObject fail() {

        JSONObject json = fail(FAIL_MSG);

        return json;
    }

}
