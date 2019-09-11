package com.yiling.pioneer.mapper;

import com.yiling.pioneer.model.Competition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: xc
 * @Date: 2019/9/7 20:05
 * @Description:
 **/
@Mapper
@Repository
public interface CompetitionMapper {
    /**
     * 获得所有未到期比赛信息
     * @return
     */
    @Select("select * from competition where expire = 0")
    List<Competition> getAllCompetition();

    /**
     * 根据比赛ID获得比赛信息
     * @param cID
     * @return
     */
    @Select("select cID,time,place,content,expire,title from competition where cID = #{cID}")
    Competition getCompetitionInfoByCID(@Param("cID") String cID);

    @Select("select title,place,amount from competition where cID = #{cID}")
    Competition getPayInfoByCID(@Param("cID") String cID);

}
