package com.yiling.pioneer.mapper;

import com.yiling.pioneer.dto.entity.GameIndex;
import com.yiling.pioneer.model.Competition;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Seagull_gby
 * @date 2019/9/8 14:57
 * Description: 管理端比赛数据库操作
 */

@Mapper
@Repository
public interface AdminGameMapper {

    /**
     * 插入一条比赛记录
     * @param competition 比赛记录实体
     * @return 插入条数
     */
    @Insert("INSERT INTO competition(cID, title, time, content, totalNum, place) values(#{cID}, #{title}, #{time}, #{content}, #{totalNum}, #{place})")
    public int insertGame(Competition competition);

    /**
     * 更新指定比赛记录
     * @param competition 比赛记录实体
     * @return 更新条数
     */
    @Update("UPDATE competition SET title = #{title}, content = #{content}, totalNum = #{totalNum}, time = #{time}, place = #{place} WHERE cID = #{cID}")
    public int updateGame(Competition competition);

    /**
     * 删除指定比赛记录
     * @param gameId 比赛编号
     * @return 删除条目
     */
    @Delete("DELETE FROM competition WHERE cID = #{gameId}")
    public int deleteGame(Long gameId);

    /**
     * 查询指定比赛记录
     * @param gameId 比赛编号
     * @return 比赛记录实体
     */
    @Select("SELECT * FROM competition WHERE cID = #{gameId}")
    public Competition queryGameOfId(Long gameId);

    /**
     * 查询所有比赛记录指引
     * 只包含ID和标题
     * @return 比赛指引集合
     */
    @Results(
            value = {
                    @Result(column = "cID", property = "gameId"),
                    @Result(column = "title", property = "gameTitle")
            }
    )
    @Select("SELECT cID,title FROM competition")
    public List<GameIndex> queryAllGame();
}
