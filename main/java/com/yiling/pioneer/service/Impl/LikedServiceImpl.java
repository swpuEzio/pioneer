package com.yiling.pioneer.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.yiling.pioneer.enums.LikedStatusEnum;
import com.yiling.pioneer.mapper.ArticleLikeMapper;
import com.yiling.pioneer.mapper.ArticleMapper;
import com.yiling.pioneer.mapper.MyUserMapper;
import com.yiling.pioneer.model.Article;
import com.yiling.pioneer.model.ArticleLike;
import com.yiling.pioneer.model.MyUser;
import com.yiling.pioneer.service.ArticleLikeService;
import com.yiling.pioneer.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class LikedServiceImpl implements ArticleLikeService {

    @Autowired
    ArticleLikeMapper articleLikeMapper;

    @Autowired
    RedisService redisService;

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    MyUserMapper myUserMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject save(ArticleLike articleLike) {

        JSONObject jsonObject = new JSONObject();
        if (articleLikeMapper.add(articleLike)){
            jsonObject.put("status",200);
            System.out.println("记录成功");
        }else {
            jsonObject.put("status",500);
            System.out.println("记录失败");

        }
        return jsonObject;
    }
    @Override
    public void delete(ArticleLike articleLike){
        if(articleLikeMapper.delete(articleLike)){
            System.out.println("删除成功");
        }else{
            System.out.println("删除失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject saveAll(List<ArticleLike> list) {
        return null;

    }

    @Override
    public JSONObject getLikedListByLikedUserId(String likedUserId) {
        List<ArticleLike> articleLikes = articleLikeMapper.getLikedListByLikedUserId(likedUserId);
        JSONObject jsonObject = new JSONObject();
        if (articleLikes.isEmpty()){
            jsonObject.put("status",404);
            jsonObject.put("msg","该用户没有点赞的文章");
            return jsonObject;
        }
        JSONArray jsonArray = new JSONArray();
        for (ArticleLike articleLike:articleLikes){
            JSONObject object = new JSONObject();
            object.put("articleID",articleLike.getArticleID());
            object.put("title",articleMapper.getArticleByArticleID(articleLike.getArticleID()).getTitle());
            jsonArray.add(object);
        }
        jsonObject.put("status",200);
        jsonObject.put("count",articleLikes.size());
        jsonObject.put("result",jsonArray);
        return jsonObject;

    }

    @Override
    public JSONObject getLikedListByLikedPostId(String likedPostId) {
        List<ArticleLike> articleLikes = articleLikeMapper.getLikedListByLikedPostId(likedPostId);
        JSONObject jsonObject = new JSONObject();
        if (articleLikes.isEmpty()){
            jsonObject.put("status",404);
            jsonObject.put("msg","该文章还没有人点赞");
            return jsonObject;
        }
        JSONArray jsonArray = new JSONArray();
        for (ArticleLike articleLike:articleLikes){
            JSONObject object = new JSONObject();
            MyUser user = myUserMapper.getUserAllInfoByUID(articleLike.getUID());
            object.put("username",user.getUsername());
            object.put("nickname",user.getNickname());
            object.put("avatarUrl",user.getAvatarUrl());
            jsonArray.add(object);
        }
        jsonObject.put("status",200);
        jsonObject.put("count",articleLikes.size());
        jsonObject.put("result",jsonArray);
        return jsonObject;
    }

    @Override
    public ArticleLike getByLikedUserIdAndLikedPostId(String likedUserId, String likedPostId) {
       return articleLikeMapper.getByLikedUserIdAndLikedPostId(likedPostId,likedUserId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void transLikedFromRedis2DB() {
        List<ArticleLike> list = redisService.getLikedDataFromRedis();
        for (ArticleLike like : list) {
                ArticleLike ul = getByLikedUserIdAndLikedPostId(like.getUID(), like.getArticleID());
                if (ul == null){
                    //没有记录，直接存入
                    save(like);
                }else{
                    //有记录，删除
                    delete(ul);
                }
        }
    }

//    @Override
//    @Transactional
//    public void transLikedCountFromRedis2DB() {
//        List<LikedCount> list = redisService.getLikedCountFromRedis();
//        for (LikedCount dto : list) {
//            UserInfo user = userService.findById(dto.getId());
//            //点赞数量属于无关紧要的操作，出错无需抛异常
//            if (user != null){
//                Integer likeNum = user.getLikeNum() + dto.getCount();
//                user.setLikeNum(likeNum);
//                //更新点赞数量
//                userService.updateInfo(user);
//            }
//        }
//    }
}

