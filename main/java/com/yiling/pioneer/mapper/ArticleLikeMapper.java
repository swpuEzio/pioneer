package com.yiling.pioneer.mapper;

import com.yiling.pioneer.model.ArticleLike;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: xc
 * @Date: 2019/8/27 16:03
 * @Description:
 **/
@Mapper
@Repository
public interface ArticleLikeMapper {
    /**
     * 插入点赞记录
     * @param articleLike
     * @return
     */
    @Insert("insert into article_like_record(articleID,uID) values(#{articleID},#{uID})")
    boolean add(ArticleLike articleLike);

    /**
     * 删除点赞记录
     * @param articleLike
     * @return
     */
    @Delete("delete from article_like_record where articleID = #{articleID} and uID = #{uID}")
    boolean delete(ArticleLike articleLike);
    /**
     * 按照用户标识ID查找点赞文章ID
     * @param uID
     * @return
     */
    @Select("select * from article_like_record where uID = #{uID} order by id desc")
    List<ArticleLike> getLikedListByLikedUserId(@Param("uID") String uID);

    @Select("select * from article_like_record where articleID = #{articleID} order by id desc")
    List<ArticleLike> getLikedListByLikedPostId(@Param("articleID") String articleID);

    /**
     * 目标用户对目标文章是否点赞
     * @return
     */
    @Select("select * from article_like_record where articleID = #{articleID} and uID = #{uID}")
    ArticleLike getByLikedUserIdAndLikedPostId(@Param("articleID") String articleID,@Param("uID") String uID);
}
