package com.yiling.pioneer.mapper;

import com.yiling.pioneer.model.MyUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: xc
 * @Date: 2019/7/7 13:53
 * @Description:
 **/
@Mapper
@Repository
public interface MyUserMapper {

    @Select("select user.name,user.nickname,article.title from user left join article on user.uID = article.authorID")
    List<Map> test();

    /**
     * 根据username获得用户信息
     * @param username 用户名
     * @return 用户实体类
     */
    @Select("select * from user where username = #{username}")
    MyUser getUserAllInfoByUserName(@Param("username") String username);

    /**
     * 根据uID获得用户信息
     * @param uID
     * @return
     */
    @Select("select * from user where uID = #{uID}")
    MyUser getUserAllInfoByUID(@Param("uID") String uID);
    /**
     * 获得已存在所有用户的电话和昵称
     * @return 所有已存在的信息List集合
     */
    @Select("select username,nickname from user")
    List<Map> getAllPhoneAndNickname();

    /**
     * 插入一个新用户
     * @param myUser 用户实体类
     * @return false-添加失败；true-添加成功
     */
    @Insert("insert into user(username,password,uID,role,name,nickname,avatarUrl) values" +
            "                (#{username},#{password},#{uID},#{role},#{name},#{nickname},#{avatarUrl})")
    boolean addUser(MyUser myUser);

    /**
     * 更新密码
     * @param username
     * @param password
     * @return false-更新失败；true-更新成功
     */
    @Update("update user set password = #{password} where username = #{username}")
    boolean updatePasswoed(@Param("username") String username,@Param("password") String password);
}
