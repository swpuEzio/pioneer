package com.yiling.pioneer.controller;

import com.alibaba.fastjson.JSONObject;
import com.yiling.pioneer.mapper.CommentMapper;
import com.yiling.pioneer.model.Comment;
import com.yiling.pioneer.model.MyUser;
import com.yiling.pioneer.service.CommentService;
import com.yiling.pioneer.service.MyUserService;
import com.yiling.pioneer.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;

/**
 * @Author: xc
 * @Date: 2019/9/4 16:46
 * @Description:
 **/
@CrossOrigin
@RestController
public class CommentController {

    @Autowired
    CommentService commentService;
    @Autowired
    MyUserService myUserService;

    @GetMapping("/getAllCommentByArticleID")
    public JSONObject getAllCommentByArticleID(@RequestParam("articleID") String articleID,
                                               @RequestParam("row") String row,
                                               @RequestParam("pageNum")  String pageNum){
        return commentService.getAllCommentByArticleID(articleID,Integer.parseInt(row),Integer.parseInt(pageNum));
    }
    @PostMapping("/publishComment")
    public JSONObject publishComment(Comment comment,
                                     @AuthenticationPrincipal Principal principal){

        String username = principal.getName();
        MyUser current = myUserService.getUserAllInfoByUserName(username);
        comment.setCommentID(TimeUtils.TimeCrateID());
        comment.setSendTime(TimeUtils.formatTime(new Date()));
        comment.setUID(String.valueOf(current.getUID()));
        return commentService.addComment(comment);
    }
    @GetMapping("/getUserReply")
    public JSONObject getUserReply(@RequestParam("row") String row,@RequestParam("pageNum") String pageNum,
                                    @AuthenticationPrincipal Principal principal ){
        String username = principal.getName();
        MyUser current = myUserService.getUserAllInfoByUserName(username);
        return commentService.getAllCommentByLastUID(String.valueOf(current.getUID()),Integer.parseInt(row),Integer.parseInt(pageNum));
    }
    @GetMapping("/getUnReadCommentNum")
    public JSONObject getUnReadCommentNum(@RequestParam("articleID") String articleID){
        return commentService.getUnReadCommentNum(articleID);
    }
    @GetMapping("/getUnReadReplyNum")
    public JSONObject getUnReadReplyNum(@AuthenticationPrincipal Principal principal){
        String username = principal.getName();
        MyUser current = myUserService.getUserAllInfoByUserName(username);
        return commentService.getUnReadReplyNum(String.valueOf(current.getUID()));
    }

    @PostMapping("/updateStatusByArticleID")
    public JSONObject updateStatusByArticleID(@RequestParam("articleID") String articleID){
        return commentService.updateStatusByArticleID(articleID);
    }
    @PostMapping("/updateReplyStatusByLastUID")
    public JSONObject updateReplyStatusByLastUID(@AuthenticationPrincipal Principal principal){
        String username = principal.getName();
        MyUser current = myUserService.getUserAllInfoByUserName(username);
        return commentService.updateReplyStatusByLastUID(String.valueOf(current.getUID()));
    }
    @GetMapping("/getReplyByReplyCommentID")
    public JSONObject getReplyByReplyCommentID(@RequestParam("replyCommentID") String replyCommentID){
        return commentService.getReplyByReplyCommentID(replyCommentID);
    }
}
