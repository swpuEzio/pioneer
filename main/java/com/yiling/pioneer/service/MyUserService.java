package com.yiling.pioneer.service;


import com.alibaba.fastjson.JSONObject;
import com.yiling.pioneer.model.MyUser;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @Author: xc
 * @Date: 2019/7/7 16:20
 * @Description:
 **/
@Service
public interface MyUserService {
    /**
     * 获得用户信息
     * @param username 用户账户
     * @return 用户实体类
     */
    MyUser getUserAllInfoByUserName(String username);

    /**
     * 注册用户
     * @param IDCard 身份证号
     * @param name 姓名
     * @param phoneNum  电话号码
     * @param nickname 昵称
     * @return {
     *       "status": "状态码",
     *       "message": "信息"
     * }
     */
    JSONObject addUser(String IDCard,String name,String phoneNum,String nickname);

    /**
     * 更新密码
     * @param username 用户名
     * @param password 新密码
     * @return {
     *       "status": "状态码",
     *       "message": "信息"
     * }
     */
    JSONObject updatePassword(String username,String password);
}
