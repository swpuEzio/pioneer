package com.yiling.pioneer.dto.json;


import com.alibaba.fastjson.JSONObject;

/**
 * @author Seagull_gby
 * @date 2019/6/1 17:23
 * Description: Json工厂类
 */

public class JsonContext<T> {

    /**
     * 具体jsonResult对象
     */
    private JsonResult<T> jsonResult;

    /**
     * 构造方法传入具体jsonResult对象
     * @param jsonResult 具体jsonResult对象
     */
    public JsonContext(JsonResult<T> jsonResult) {
        this.jsonResult = jsonResult;
    }

    /**
     * 成功状态策略对象实现方法
     * @param data 封装对象数据
     * @return JSON
     */
    public JSONObject success(T data) {
        return jsonResult.success(data);
    }

    /**
     * 失败状态策略对象实现方法
     * @param msg 失败状态信息
     * @return JSON
     */
    public JSONObject fail(String msg) {
        return jsonResult.fail(msg);
    }

    /**
     * 失败状态策略对象实现方法重载
     * @return JSON
     */
    public JSONObject fail() {
        return jsonResult.fail();
    }
}
