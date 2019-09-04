package com.yiling.pioneer.mapper;

import com.yiling.pioneer.model.CompanyInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @Author: xc
 * @Date: 2019/9/2 17:01
 * @Description:
 **/
@Mapper
@Repository
public interface CompanyInfoMapper {
    /**
     * 获得公司信息
     * @return
     */
    @Select("select * from company_info where id = 1")
    CompanyInfo getInfo();

    /**
     *更新公司信息
     * @param companyInfo
     * @return
     */

    @Update("update company_info set purpose = #{purpose}," +
                                    "idea = #{idea}," +
                                    "goal = #{goal}," +
                                    "resources = #{resources}," +
                                    "strategy = {strategy}," +
                                    "slogan = {slogan}" +
                                    " where id = 1")
    boolean updateInfo(CompanyInfo companyInfo);
}
