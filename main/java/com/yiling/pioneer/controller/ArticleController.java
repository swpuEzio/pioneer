package com.yiling.pioneer.controller;

import com.alibaba.fastjson.JSONObject;
import com.yiling.pioneer.mapper.ArticleMapper;
import com.yiling.pioneer.mapper.MediaMapper;
import com.yiling.pioneer.mapper.MyUserMapper;
import com.yiling.pioneer.model.Article;
import com.yiling.pioneer.model.MyUser;
import com.yiling.pioneer.service.ArticleLikeService;
import com.yiling.pioneer.service.ArticleService;
import com.yiling.pioneer.service.MediaService;
import com.yiling.pioneer.service.MyUserService;
import com.yiling.pioneer.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: xc
 * @Date: 2019/8/25 18:52
 * @Description:
 **/
@CrossOrigin
@RestController
public class ArticleController {
    @Autowired
    MyUserService myUserService;
    @Autowired
    MediaService mediaService;
    @Autowired
    ArticleService articleService;
    @Autowired
    ArticleLikeService articleLikeService;
    @PostMapping("/editor/publish")
    public JSONObject publish(HttpServletRequest request,
                              Article article,
                              @AuthenticationPrincipal Principal principal){
        try {
            MyUser currentUser = myUserService.getUserAllInfoByUserName(principal.getName());
            String articleID = TimeUtils.TimeCrateID();
            article.setArticleID(Integer.parseInt(articleID));
            article.setAuthorID(currentUser.getUID());
            String videoID = (String) request.getSession().getAttribute("videoID");
            if (videoID!=null){
                mediaService.setArticleId(videoID,articleID);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = sdf.format(new Date());
            article.setSendTime(time);
            JSONObject jsonObject = articleService.add(article);
            if (jsonObject.getString("status").equals("200")){
                request.removeAttribute("videoID");
            }
            return jsonObject;
        }catch (Exception e){
            e.printStackTrace();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status",501);
            return jsonObject;
        }
    }
    @GetMapping("/getAllCheckedArticle")
    public JSONObject getAllCheckedArticle(@RequestParam("pageNum") String pageNum,@RequestParam("row") String row){
        return articleService.getCheckedArticle(Integer.parseInt(pageNum),Integer.parseInt(row));
    }

    @GetMapping("/report")
    public JSONObject report(@RequestParam("articleID") String articleID){
        return articleService.updateArticleStatus(articleID);
    }
    @GetMapping("/getArticleInfoByID")
    public JSONObject articleInfo(@RequestParam("articleID") String articleID){
        JSONObject articleJson = articleService.getArticleByArticleID(articleID);
        JSONObject likeJson = articleLikeService.getLikedListByLikedPostId(articleID);
        JSONObject reJson = new JSONObject();
        reJson.put("articleJson",articleJson);
        reJson.put("likeJson",likeJson);
        return reJson;
    }
}
