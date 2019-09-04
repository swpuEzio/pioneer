package com.yiling.pioneer.service;

import com.alibaba.fastjson.JSONObject;
import com.yiling.pioneer.model.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: xc
 * @Date: 2019/9/4 14:59
 * @Description:
 **/
@Service
public interface CommentService {
    /**
     * 新增评论
     * @param comment
     * @return
     */
    JSONObject addComment(Comment comment);

    /**
     * 获得文章所有评论
     * @param articleID
     * @return
     */
    JSONObject getAllCommentByArticleID(String articleID,int row,int pageNum);

    /**
     * 获得回复
     * @param lastUID
     * @return
     */
    JSONObject getAllCommentByLastUID(String lastUID,int row,int pageNum);

    /**
     * 更新文章状态
     * @param articleID
     * @return
     */
    JSONObject updateStatusByArticleID(String articleID);

    /**
     * 更新回复状态
     * @param lastUID
     * @return
     */
    JSONObject updateReplyStatusByLastUID(String lastUID);

    /**
     * 获得未读评论
     * @param articleID
     * @return
     */
    JSONObject getUnReadCommentNum(String articleID);

    /**
     * 获得未读回复
     * @param lastUID
     * @return
     */
    JSONObject getUnReadReplyNum(String lastUID);
}
