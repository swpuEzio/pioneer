package com.yiling.pioneer.mapper;

import com.yiling.pioneer.dto.entity.SubmissionContent;
import com.yiling.pioneer.dto.entity.UserSubmission;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Seagull_gby
 * @date 2019/9/5 15:06
 * Description: 用户投稿数据库处理
 */

@Mapper
@Repository
public interface SubmissionMapper {

    /**
     * 查询用户投稿（未审核状态）
     * @return 用户投稿集合
     */
    @Results(
            value = {
                    @Result(column = "title", property = "articleTitle"),
                    @Result(column = "username", property = "userName")
            }
    )
    @Select("SELECT a.articleID,a.title,u.username FROM article as a " +
            "LEFT JOIN user as u ON a.authorID = u.uID " +
            "WHERE status = 0 ")
    public List<UserSubmission> queryUserSubmission();

    /**
     * 查询投稿详细内容信息（未审核状态）
     * @param articleId 文章编号
     * @return 投稿详细信息
     */
    @Results(
            value = {
                    @Result(column = "title", property = "articleTitle"),
                    @Result(column = "username", property = "userName"),
                    @Result(column = "content", property = "articleContent"),
                    @Result(column = "url", property = "videoUrl")
            }
    )
    @Select("SELECT a.title,a.content,u.username,m.url FROM article as a " +
            "LEFT JOIN media as m ON a.articleID = m.articleID " +
            "LEFT JOIN user as u ON a.authorID = u.uID " +
            "WHERE status = 0 AND a.articleID = #{articleId}")
    public SubmissionContent querySubmissionContent(Long articleId);

    /**
     * 投稿审核状态更新
     * @param articleId 文章编号
     * @param auditResult 审核结果
     * @return 更新条数
     */
    @Update("UPDATE article SET status = #{auditResult} WHERE articleID = #{articleId} AND status = 0")
    public int updateSubmissionState(@Param("articleId") Long articleId, @Param("auditResult") Integer auditResult);
}
