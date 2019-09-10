package com.yiling.pioneer.dto.json;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Seagull_gby
 * @date 2019/9/8 10:54
 * Description: 用户操作JSON封装
 */

public class UserJsonResult<T> extends ListBaseJsonResult<T> {

    @Override
    public JSONObject success(T data) {
        return super.success(data);
    }

    @Override
    public JSONObject fail(String msg) {
        return super.fail(msg);
    }

    @Override
    public JSONObject fail() {
        return super.fail();
    }
}
