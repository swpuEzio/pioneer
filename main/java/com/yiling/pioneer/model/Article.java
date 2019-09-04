package com.yiling.pioneer.model;

import lombok.Data;

/**
 * @Author: xc
 * @Date: 2019/7/31 11:54
 * @Description: 文章投稿实体类
 **/
@Data
public class Article {
    /**
     * 序列id
     */
    private int id;

    /**
     * 标识ID
     */
    private int articleID;
    /**
     * 作者标识ID
     */
    private int authorID;
    /**
     * 标题
     */
    private String title ;
    /**
     * 发布时间
     */
    private String sendTime;
    /**
     * 内容
     */
    private String content;
    /**
     * 点赞数量
     */
    private int likes;
    /**
     * 审核状态
     */
    private int status;
}
