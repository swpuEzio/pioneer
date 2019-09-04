package com.yiling.pioneer.mapper;

import com.yiling.pioneer.model.Media;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: xc
 * @Date: 2019/8/5 16:51
 * @Description:
 **/
@Mapper
@Repository
public interface MediaMapper {
    /**
     * 获得目标文章下的所有媒体链接
     * @param articleID
     * @return
     */
    @Select("select * from media where articleID = #{articleID}")
    Media getUrlByArticleID(@Param("article") int articleID);

    /**
     * 插入记录
     * @param name 文件名
     * @param url oss外网链接
     * @return
     */
    @Insert("insert into media(name,url) values (#{name},#{url})")
    boolean add(@Param("name") String name, @Param("url") String url);

    /**
     * 根据文件名插入文章ID
     * @param name 视频名
     * @param articleId 文章ID
     * @return Boolean
     */
    @Insert("insert into media(articleId) value(#{articleId) where name = #{name}")
    boolean setArticleId(@Param("name") String name,@Param("articleId") String articleId);
}
