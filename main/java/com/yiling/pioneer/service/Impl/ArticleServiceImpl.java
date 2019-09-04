package com.yiling.pioneer.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yiling.pioneer.mapper.ArticleMapper;
import com.yiling.pioneer.mapper.MediaMapper;
import com.yiling.pioneer.model.Article;
import com.yiling.pioneer.model.Media;
import com.yiling.pioneer.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: xc
 * @Date: 2019/8/25 18:51
 * @Description:
 **/
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    MediaMapper mediaMapper;
    @Override
    public JSONObject getArticleByArticleID(String articleID) {
        JSONObject jsonObject = new JSONObject();
        Article article = articleMapper.getArticleByArticleID(articleID);
        jsonObject.put("authorID",article.getAuthorID());
        jsonObject.put("title",article.getTitle());
        jsonObject.put("sendTime",article.getSendTime());
        jsonObject.put("content",article.getContent());
        jsonObject.put("mediaUrl",mediaMapper.getUrlByArticleID(Integer.parseInt(articleID)));
        return jsonObject;
    }

    @Override
    public JSONObject getNotCheckedArticle() {
        return null;
    }

    @Override
    public JSONObject getCheckedArticle( int pageNum,int rows) {
        PageHelper.startPage(pageNum,rows);
        List<Article> articles = articleMapper.getCheckedArticle();
        JSONObject rejson = new JSONObject();
        PageInfo<Article> pageInfo = new PageInfo<>(articles);

        JSONObject pageJson = new JSONObject();
        pageJson.put("pageNum",pageInfo.getPageNum());
        pageJson.put("pageSize",pageInfo.getPageSize());
        pageJson.put("total",pageInfo.getTotal());
        pageJson.put("pages",pageInfo.getPages());
        pageJson.put("isFirstPage",pageInfo.isIsFirstPage());
        pageJson.put("isLastPage",pageInfo.isIsLastPage());
        if (articles.isEmpty()){
            rejson.put("status",404);
            return rejson;
        }
        JSONArray result = new JSONArray();
        for (Article article:articles){
            Media media = mediaMapper.getUrlByArticleID(article.getArticleID());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("articleID",article.getArticleID());
            jsonObject.put("authorID",article.getAuthorID());
            jsonObject.put("title",article.getTitle());
            jsonObject.put("sendTime",article.getSendTime());
            jsonObject.put("content",article.getContent());
            jsonObject.put("like",article.getLikes());
            jsonObject.put("mediaUrl",media.getUrl());
            result.add(jsonObject);
        }
        rejson.put("status",200);
        rejson.put("pageJson",pageJson);
        rejson.put("result",result);
        return rejson;
    }

    @Override
    public JSONObject add(Article article) {
        JSONObject jsonObject = new JSONObject();
        if (articleMapper.add(article)){
            jsonObject.put("status",200);
        }else {
            jsonObject.put("status",500);
        }
        return jsonObject;
    }

    @Override
    public JSONObject updateArticleStatus(String articleID) {
        JSONObject jsonObject = new JSONObject();
        int currentNum = articleMapper.getStatusByArticleID(articleID);
        int num = currentNum++;
        if (articleMapper.updateArticleStatus(articleID,num)){
            jsonObject.put("status",200);
        }else {
            jsonObject.put("status",500);
        }
        return jsonObject;
    }
}
