package com.yiling.pioneer.mapper;

import com.yiling.pioneer.model.Article;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: xc
 * @Date: 2019/8/5 15:45
 * @Description:
 **/
@Mapper
@Repository
public interface ArticleMapper {
    /**
     * 存储文章
     * @param article
     * @return
     */
    @Insert("insert into article(articleID,authorID,title,sendTime,content) values" +
                                "(#{articleID},#{authorID},#{title},#{sendTime},#{content})")
    boolean add(Article article);

    /**
     * 获得已通过审核的文章，按时间降序
     * @return
     */
    @Select("select * from article where status <= 10 order by sendTime desc")
    List<Article> getCheckedArticle();

    /**
     * 获得未审核的文章
     * @return
     */
    @Select("select * from article where status = 0")
    List<Article> getNotCheckedArticle();

    /**
     * 按作者ID获得文章
     * @param authorID 用户标识ID
     * @return
     */
    @Select("select * from article where authorID = #{authorID}")
    List<Article> getArticleByAuthorID(@Param("authorID") String authorID);

    /**
     * 获得目标文章的状态
     * @param articleID
     * @return
     */
    @Select("select status from article where articleID = #{articleID}")
    int getStatusByArticleID(@Param("articleID") String articleID);

    /**
     * 更新目标文件的Status值
     * @param articleID
     * @param num
     * @return
     */
    @Update("update article set status = #{num} where articleID = #{articleID}")
    boolean updateArticleStatus(@Param("articleID") String articleID,@Param("num") int num);

    /**
     * 按文章ID获取文章信息
     * @param articleID
     * @return
     */
    @Select("select * from article where articleID = #{articleID}")
    Article getArticleByArticleID(String articleID);
}
