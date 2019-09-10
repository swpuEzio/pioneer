package com.yiling.pioneer.dto.json;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Seagull_gby
 * @date 2019/5/31 20:14
 * Description: JSON结果抽象类
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class JsonResult<T> {

    /**
     * 失败参数默认信息
     */
    protected static final String FAIL_MSG = "fail";

    /**
     * 状态码
     */
    protected Integer code;

    /**
     * 状态信息
     */
    protected String msg;

    /**
     * 封装的具体数据
     */
    protected T data;

    /**
     * 成功状态
     * @param data 具体数据
     * @return JSON
     */
    public abstract JSONObject success(T data);

    /**
     * 失败状态
     * @param msg 失败信息
     * @return JSON
     */
    public abstract JSONObject fail(String msg);

    /**
     * 失败状态重载方法，可设置默认值
     * @return JSON
     */
    public abstract JSONObject fail();
}
