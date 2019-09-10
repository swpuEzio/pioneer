package com.yiling.pioneer.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author Seagull_gby
 * @date 2019/5/31 21:20
 * Description: 转化JSON工具类
 */
public class ToJsonUtil {

    /**
     * 将类属性转化为JSON格式
     * 如：属性有id、name
     * 则返回的JSON为： {"id": id, "name" : name}
     * 其中id,name为属性的值
     * 若为null则不加入JSON中
     * @param data 对应类
     * @return JSON
     */
    public static JSONObject toJson(Object data) {

        JSONObject dataJSON = (JSONObject) JSONObject.toJSON(data);

        return dataJSON;
    }

    /**
     * 将集合转化为JSONArray格式
     * @param data 对应集合
     * @return JSONArray
     */
    public static JSONArray toJsonArray(List data) {

        JSONArray dataJSONArray = (JSONArray) JSONArray.toJSON(data);

        return dataJSONArray;
    }
}
