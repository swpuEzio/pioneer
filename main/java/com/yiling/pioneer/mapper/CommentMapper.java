package com.yiling.pioneer.mapper;

import com.yiling.pioneer.model.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: xc
 * @Date: 2019/9/4 11:00
 * @Description:
 **/
@Mapper
@Repository
public interface CommentMapper {
    /**
     * 新增评论
     * @param comment
     * @return
     */
    @Insert("insert into comment(commentID,articleID,uID,content,sendTime,replyCommentID,lastUID) values" +
                                "(#{commentID},#{articleID},#{uID},#{content},#{sendTime},#{replyCommentID},#{lastUID})")
    boolean addComment(Comment comment);

    /**
     * 获取文章所有评论，时间降序
     * @param articleID
     * @return
     */
    @Select("select * from comment where articleID = #{articleID} order by sendTime desc")
    List<Comment> getAllCommentByArticleID(@Param("articleID") String articleID);

    /**
     * 获得目标用户被回复的评论
     * @param
     * @return
     */
    @Select("select * from comment where lastUID = #{lastUID} order by sendTime desc")
    List<Comment> getAllCommentByLastUID(@Param("lastUID") String lastUID);

    /**
     * 更新评论状态
     * @param articleID
     * @return
     */
    @Update("update comment set status = 1 where articleID = #{articleID}")
    boolean updateStatusByArticleID(@Param("articleID") String articleID);

    /**
     * 更新回复状态
     * @param lastUID
     * @return
     */
    @Update("update comment set status = 1 where lastUID = #{lastUID}")
    boolean updateReplyStatusByLastUID(@Param("lastUID") String lastUID);

    /**
     * 获得未读评论 降序
     */
    @Select("select * from comment where articleID = #{articleID} and status = 0 order by sendTime desc")
    List<Comment> getUnReadComment(@Param("articleID") String articleID);

    /**
     * 获得未读回复
     * @param lastUID
     * @return
     */
    @Select("select * from comment where lastUID = #{lastUID} and replyStatus = 0 order by sendTime desc")
    List<Comment> getUnReadReply(@Param("lastUID") String lastUID);

    /**
     * 获得目标评论的回复
     * @param replyCommentID
     * @return
     */
    @Select("select * from comment where replyCommentID = #{replyCommentID}")
    List<Comment> getReplyByReplyCommentID(@Param("replyCommentID") String replyCommentID);
}
