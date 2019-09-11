package com.yiling.pioneer.mapper;

import com.yiling.pioneer.model.GameRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: xc
 * @Date: 2019/9/11 13:30
 * @Description:
 **/
@Mapper
@Repository
public interface GameRecordMapper {

    /**
     * 插入报名记录
     * @param gameRecord
     * @return
     */
    @Insert("insert into game_record(uID,cID) values(#{uID},#{cID})")
    boolean addRecord(GameRecord gameRecord);

    /**
     * 获得用户报名的比赛
     * @param uID
     * @return
     */
    @Select("select * from game_record where uID=#{uID}")
    List<GameRecord> getRecordByuID(String uID);

    /**
     * 获得比赛报名用户
     * @param cID
     * @return
     */
    @Select("select * from game_record where cID=#{cID}")
    List<GameRecord> getRecordBycID(String cID);
}
