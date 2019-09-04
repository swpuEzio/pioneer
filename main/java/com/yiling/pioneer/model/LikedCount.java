package com.yiling.pioneer.model;

import lombok.Data;

/**
 * @Author: xc
 * @Date: 2019/8/27 14:05
 * @Description:
 **/
@Data
public class LikedCount {
    /**
     * 点赞信息
     */
    private String info;
    /**
     * 点赞状态
     */
    private int status;


    public LikedCount(String info, int status) {
        this.info = info;
        this.status = status;
    }
}
