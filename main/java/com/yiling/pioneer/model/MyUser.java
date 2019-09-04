package com.yiling.pioneer.model;

import lombok.Data;

/**
 * @Author: xc
 * @Date: 2019/7/7 13:13
 * @Description: 用户实体
 **/
@Data
public class MyUser {
    private String username;
    private String password;
    private Integer uID;
    private Integer violateNum;
    private String role;
    private String name;
    private String IDCard;
    private String nickname;
    private String avatarUrl;

}
