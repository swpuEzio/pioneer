package com.yiling.pioneer.controller;

import com.alibaba.fastjson.JSONObject;
import com.yiling.pioneer.mapper.ArticleMapper;
import com.yiling.pioneer.mapper.MyUserMapper;
import com.yiling.pioneer.model.Article;
import com.yiling.pioneer.model.ImpeachRecord;
import com.yiling.pioneer.model.MyUser;
import com.yiling.pioneer.service.ImpeachRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @Author: xc
 * @Date: 2019/9/5 17:20
 * @Description:
 **/
@CrossOrigin
@RestController
public class ImpeachRecordController {
    @Autowired
    MyUserMapper myUserMapper;
    @Autowired
    ImpeachRecordService impeachRecordService;
    @Autowired
    ArticleMapper articleMapper;
    @PostMapping("/report")
    public JSONObject report(@RequestParam("articleID") String articleID,
                             @RequestParam("content") String content,
                             @AuthenticationPrincipal Principal principal){
        String username = principal.getName();
        ImpeachRecord impeachRecord = new ImpeachRecord();
        Article article = articleMapper.getArticleByArticleID(articleID);
        MyUser current =myUserMapper.getUserAllInfoByUID(username);
        impeachRecord.setReporteeUID(current.getUID());
        impeachRecord.setReporteeUID(article.getAuthorID());
        impeachRecord.setArticleID(Integer.parseInt(articleID));
        impeachRecord.setContent(content);
        return impeachRecordService.addRecord(impeachRecord);
    }
}
