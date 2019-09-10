package com.yiling.pioneer.mapper;

import com.yiling.pioneer.dto.entity.UserFrozen;
import com.yiling.pioneer.dto.entity.UserViolation;
import com.yiling.pioneer.dto.entity.UserViolationReason;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Seagull_gby
 * @date 2019/9/6 19:37
 * Description: 管理端用户数据库操作
 */

@Mapper
@Repository
public interface AdminUserMapper {

    /**
     * 查询违规用户
     * @return 违规用户集合
     */
    @Results(
            id = "user",
            value = {
                  @Result(column = "uID", property = "userId"),
                  @Result(column = "username", property = "userName")
            }
    )
    @Select("SELECT uID,username,violateNum FROM user WHERE violateNum > 0 ORDER BY violateNum DESC")
    public List<UserViolation> queryViolateUser();

    /**
     * 查询用户所有被举报原因
     * @param userId 用户ID
     * @return 举报原因集合
     */
    @Results(
            value = {
                    @Result(column = "content", property = "violationReason")
            }
    )
    @Select("SELECT content,articleID FROM impeach_record WHERE reporteeUID = #{userId}")
    public List<UserViolationReason> queryViolationReasonOfUser(Long userId);

    /**
     * 查询所有被冻结的用户
     * @return 冻结用户列表
     */
    @ResultMap("user")
    @Select("SELECT uID,username FROM user WHERE role = 'ROLE_BREAKER'")
    public List<UserFrozen> queryFrozenUsers();

    /**
     * 更新用户权限（冻结用户）
     * @param userName 用户昵称
     * @return 更新数量
     */
    @Update("UPDATE user SET role = 'ROLE_BREAKER' WHERE username = #{userName} AND role != 'ROLE_BREAKER'")
    public int updateUserRoleOfBreaker(String userName);

}
