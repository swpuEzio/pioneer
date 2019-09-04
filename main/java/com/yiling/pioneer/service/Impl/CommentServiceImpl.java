package com.yiling.pioneer.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yiling.pioneer.mapper.CommentMapper;
import com.yiling.pioneer.mapper.MyUserMapper;
import com.yiling.pioneer.model.Article;
import com.yiling.pioneer.model.Comment;
import com.yiling.pioneer.model.MyUser;
import com.yiling.pioneer.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: xc
 * @Date: 2019/9/4 15:49
 * @Description:
 **/
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    MyUserMapper myUserMapper;
    @Override
    public JSONObject addComment(Comment comment) {
        JSONObject jsonObject = new JSONObject();
        if (commentMapper.addComment(comment)){
            jsonObject.put("status",200);
        }else {
            jsonObject.put("status",500);
        }
        return jsonObject;
    }

    @Override
    public JSONObject getAllCommentByArticleID(String articleID,int row,int pageNum) {
        PageHelper.startPage(pageNum,row);
        List<Comment> comments = commentMapper.getAllCommentByArticleID(articleID);
        JSONObject reJson = new JSONObject();
        if(comments.isEmpty()){
            reJson.put("status",404);
            return reJson;
        }
        PageInfo<Comment> pageInfo = new PageInfo<>(comments);
        JSONObject pageJson = new JSONObject();
        pageJson.put("pageNum",pageInfo.getPageNum());
        pageJson.put("pageSize",pageInfo.getPageSize());
        pageJson.put("total",pageInfo.getTotal());
        pageJson.put("pages",pageInfo.getPages());
        pageJson.put("isFirstPage",pageInfo.isIsFirstPage());
        pageJson.put("isLastPage",pageInfo.isIsLastPage());
        JSONArray result = new JSONArray();
        for (Comment comment:comments){
            MyUser commentUser = myUserMapper.getUserAllInfoByUID(comment.getUID());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("commentID",comment.getCommentID());
            jsonObject.put("uID",comment.getUID());
            jsonObject.put("nickname",commentUser.getNickname());
            jsonObject.put("avatarUrl",commentUser.getAvatarUrl());
            jsonObject.put("content",comment.getContent());
            jsonObject.put("sendTime",comment.getSendTime());
            jsonObject.put("replyCommentID",comment.getReplyCommentID());
            jsonObject.put("lastUID",comment.getLastUID());
            jsonObject.put("lastNickname",myUserMapper.getUserAllInfoByUID(comment.getLastUID()).getNickname());
            result.add(jsonObject);
        }

        reJson.put("status",200);
        reJson.put("pageInfo",pageJson);
        reJson.put("result",result);
        return reJson;
    }

    @Override
    public JSONObject getAllCommentByLastUID(String lastUID,int row,int pageNum) {
        PageHelper.startPage(pageNum,row);
        List<Comment> comments = commentMapper.getAllCommentByLastUID(lastUID);
        JSONObject reJson = new JSONObject();
        if(comments.isEmpty()){
            reJson.put("status",404);
            return reJson;
        }
        PageInfo<Comment> pageInfo = new PageInfo<>(comments);
        JSONObject pageJson = new JSONObject();
        pageJson.put("pageNum",pageInfo.getPageNum());
        pageJson.put("pageSize",pageInfo.getPageSize());
        pageJson.put("total",pageInfo.getTotal());
        pageJson.put("pages",pageInfo.getPages());
        pageJson.put("isFirstPage",pageInfo.isIsFirstPage());
        pageJson.put("isLastPage",pageInfo.isIsLastPage());
        JSONArray result = new JSONArray();
        for (Comment comment:comments){
            MyUser commentUser = myUserMapper.getUserAllInfoByUID(comment.getUID());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("commentID",comment.getCommentID());
            jsonObject.put("uID",comment.getUID());
            jsonObject.put("nickname",commentUser.getNickname());
            jsonObject.put("avatarUrl",commentUser.getAvatarUrl());
            jsonObject.put("content",comment.getContent());
            jsonObject.put("sendTime",comment.getSendTime());
            jsonObject.put("replyCommentID",comment.getReplyCommentID());
            jsonObject.put("lastUID",comment.getLastUID());
            jsonObject.put("lastNickname",myUserMapper.getUserAllInfoByUID(comment.getLastUID()).getNickname());
            result.add(jsonObject);
        }

        reJson.put("status",200);
        reJson.put("pageInfo",pageJson);
        reJson.put("result",result);
        return reJson;


    }

    @Override
    public JSONObject updateStatusByArticleID(String articleID) {
        JSONObject jsonObject = new JSONObject();
        if (commentMapper.updateStatusByArticleID(articleID)){
            jsonObject.put("status",200);
        }else {
            jsonObject.put("status",500);
        }
        return jsonObject;
    }

    @Override
    public JSONObject updateReplyStatusByLastUID(String lastUID) {
        JSONObject jsonObject = new JSONObject();
        if (commentMapper.updateReplyStatusByLastUID(lastUID)){
            jsonObject.put("status",200);
        }else {
            jsonObject.put("status",500);
        }
        return jsonObject;
    }

    @Override
    public JSONObject getUnReadCommentNum(String articleID) {
        JSONObject jsonObject = new JSONObject();
        List<Comment> comments = commentMapper.getUnReadComment(articleID);
        jsonObject.put("Num",comments.size());
        return jsonObject;
    }

    @Override
    public JSONObject getUnReadReplyNum(String lastUID) {
        JSONObject jsonObject = new JSONObject();
        List<Comment> comments = commentMapper.getUnReadReply(lastUID);
        jsonObject.put("Num",comments.size());
        return jsonObject;
    }
}
