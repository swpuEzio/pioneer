package com.yiling.pioneer.dto.json;

import com.alibaba.fastjson.JSONObject;
import com.yiling.pioneer.utils.ToJsonUtil;

import java.util.ArrayList;

/**
 * @author Seagull_gby
 * @date 2019/9/8 10:57
 * Description: 基础集合JSON封装
 */

public class ListBaseJsonResult<T> extends BaseJsonResult<T> {

    private static final String LIST = "ArrayList";

    @Override
    public JSONObject success(T data) {

        /* 判空取消data参数直接返回null */
        if(null == data) {
            return super.success(null);
        }

        /* 用反射机制获取类名，若为有序集合则封装为JSONArray后加入JSONObject中 */
        Class<?> cls = data.getClass();
        if(LIST.equals(cls.getSimpleName())) {
            code = 200;
            msg = "success";
            this.data = (T) ToJsonUtil.toJsonArray((ArrayList) data);

            JSONObject json = ToJsonUtil.toJson(this);
            return json;

        } else {
            return super.success(data);
        }

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
