package com.yiling.pioneer.model;

import lombok.Data;

/**
 * @Author: xc
 * @Date: 2019/9/2 19:55
 * @Description:
 **/
@Data
public class Comment {
    /**
     * 序列id
     */
    private int id;
    /**
     * 评论ID
     */
    private String commentID;
    /**
     * 评论文章ID
     */
    private String articleID;
    /**
     * 评论者uID
     */
    private String uID;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论时间
     */
    private String sendTime;
    /**
     * 被评论的评论ID
     */
    private String replyCommentID;
    /**
     * 被评论的评论者ID
     */
    private String lastUID;
    /**
     * 状态标识0未读1已读
     */
    private String status;
    /**
     * 回复状态标识
     */
    private String replyStatus;
}
