package com.yiling.pioneer.dto.json;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Seagull_gby
 * @date 2019/9/9 16:46
 * Description: 比赛JSON封装
 */

public class GameJsonResult<T> extends ListBaseJsonResult<T> {

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
