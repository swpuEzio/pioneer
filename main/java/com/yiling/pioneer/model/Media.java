package com.yiling.pioneer.model;

import lombok.Data;

/**
 * @Author: xc
 * @Date: 2019/8/5 15:43
 * @Description: 图片视频链接
 **/
@Data
public class Media {
    /**
     * 序列id
     */
    private int id;
    /**
     * 文章ID
     */
    private int articleID;
    /**
     * 文件名
     */
    private String name;
    /**
     * 存储的url
     */
    private int url;
}
