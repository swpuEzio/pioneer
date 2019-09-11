package com.yiling.pioneer.mapper;

import com.yiling.pioneer.model.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @Author: xc
 * @Date: 2019/9/11 10:44
 * @Description:
 **/
@Mapper
@Repository
public interface OrderMapper {

    /**
     * 新增订单
     * @param order
     * @return
     */
    @Insert("insert into order_record(order_no,order_amount,competition_id,create_time) " +
                        "values (#{order_no},#{order_amount},#{competition_id},#{create_time})")
    boolean addOrder(Order order);

    /**
     * 支付成功更新状态
     * @param flow_no
     * @param order_no
     * @return
     */
    @Update("update order_record set order_status = 1,flow_no = #{flow_no} where order_no = #{order_no}")
    boolean paySuccess(@Param("flow_no") String flow_no, @Param("order_no") String order_no);

    /**
     * 获取cID
     * @param order_no
     * @return
     */
    @Select("select competition_id from order_record where order_no = #{order_no}")
    String getCID(@Param("order_no") String order_no);
}
