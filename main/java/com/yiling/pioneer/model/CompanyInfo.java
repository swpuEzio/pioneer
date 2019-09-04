package com.yiling.pioneer.model;

import lombok.Data;

/**
 * @Author: xc
 * @Date: 2019/9/2 16:56
 * @Description:
 **/
@Data
public class CompanyInfo {

    /**
     * 公司成立目的
     */
    private String purpose;
    /**
     * 公司理念
     */
    private String idea;
    /**
     * 公司目标
     */
    private String goal;
    /**
     * 可利用的资源
     */
    private String resources;
    /**
     * 发展方针
     */
    private String strategy;
    /**
     * 公司口号
     */
    private String slogan;


}
