package com.yiling.pioneer.service;

import com.alibaba.fastjson.JSONObject;

import com.yiling.pioneer.model.ArticleLike;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ArticleLikeService {

    /**
     * 保存点赞记录
     * @param userLike
     * @return
     */
    JSONObject save(ArticleLike articleLike);

    /**
     * 删除点赞
     * @param articleLike
     */
    void delete(ArticleLike articleLike);
    /**
     * 批量保存或修改
     * @param list
     */
    JSONObject saveAll(List<ArticleLike> list);


    /**
     * 根据点赞人的id查询点赞列表（即查询这个人都给谁点赞过）
     * @param likedUserId 点赞人的id
     * @param pageable
     * @return
     */
    JSONObject getLikedListByLikedUserId(String likedUserId);

    /**
     * 根据被点赞人的id查询点赞列表（即查询这篇文章被谁点赞过）
     * @param likedPostId
     * @param
     * @return
     */
    JSONObject getLikedListByLikedPostId(String likedPostId);

    /**
     * 通过被点赞人和点赞人id查询是否存在点赞记录
     * @param likedUserId
     * @param likedPostId
     * @return
     */
    ArticleLike getByLikedUserIdAndLikedPostId(String likedUserId, String likedPostId);

    /**
     * 将Redis里的点赞数据存入数据库中
     */
    void transLikedFromRedis2DB();

//    /**
//     * 将Redis中的点赞数量数据存入数据库
//     */
//    void transLikedCountFromRedis2DB();

}

