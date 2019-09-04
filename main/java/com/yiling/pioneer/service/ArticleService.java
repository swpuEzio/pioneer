package com.yiling.pioneer.service;

import com.alibaba.fastjson.JSONObject;
import com.yiling.pioneer.model.Article;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: xc
 * @Date: 2019/8/25 18:38
 * @Description:
 **/
@Service
public interface ArticleService {

    /**
     * 按文章ID获得文章信息
     * @param
     * @return
     */
    JSONObject getArticleByArticleID(String articleID);

    /**
     * 获得未审核的文章
     * @return
     */
    JSONObject getNotCheckedArticle();

    /**
     * 获得已通过审核的文章，时间降序
     * @param pageNum 页数
     * @param row 每页条数
     * @return
     */
    JSONObject getCheckedArticle(int pageNum,int row);

    /**
     * 插入文章
     * @param article
     * @return
     */
    JSONObject add(Article article);

    /**
     * 更新目标文章的Status值
     * @param articleID
     * @param num
     * @return
     */
    JSONObject updateArticleStatus(String articleID);
}
