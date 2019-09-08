package com.yiling.pioneer.model;

import lombok.Data;

import java.security.Principal;

/**
 * @Author: xc
 * @Date: 2019/9/7 16:32
 * @Description: 比赛信息
 **/
@Data
public class Competition {
    /**
     * 序列ID
     */
    private int id;
    /**
     * 比赛的ID
     */
    private int cID;
    /**
     * 比赛时间
     */
    private String time;
    /**
     * 比赛地点
     */
    private String place;
    /**
     * 比赛简介
     */
    private String content;
    /**
     * 比赛规定总人数
     */
    private String totalNum;
    /**
     * 已报名参加人数
     */
    private String joinNum;
    /**
     * 是否到期，0未到期，1已到期
     */
    private String expire;
}
