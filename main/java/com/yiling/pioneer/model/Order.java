package com.yiling.pioneer.model;

import lombok.Data;

/**
 * @Author: xc
 * @Date: 2019/9/11 10:38
 * @Description: 订单实体类
 **/
@Data
public class Order {
    private int id;
    /**
     * 订单号
     */
    private String order_no;
    /**
     * 支付状态：0未支付；1已支付
     */
    private String order_status;
    /**
     * 支付金额
     */
    private float order_amount;
    /**
     * 比赛ID
     */
    private String competition_id;
    /**
     * 创建时间
     */
    private String create_time;
    /**
     * 支付流水号
     */
    private String flow_no;

}
