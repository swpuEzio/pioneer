package com.yiling.pioneer.mapper;

import com.yiling.pioneer.model.ImpeachRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: xc
 * @Date: 2019/9/5 17:11
 * @Description:
 **/
@Mapper
@Repository
public interface ImpeachRecordMapper {

    /**
     * 新增举报记录
     * @param impeachRecord
     * @return
     */
    @Insert("insert into impeach_record(reporteeUID,sendUID,articleUID,content) values" +
                                        "(#{reporteeUID},#{sendUID},#{articleUID},#{content})")
    boolean addRecord(ImpeachRecord impeachRecord);

    


}
