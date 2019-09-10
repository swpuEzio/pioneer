package com.yiling.pioneer.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Seagull_gby
 * @date 2019/9/8 13:03
 * Description: 被冻结用户封装
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFrozen {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户昵称
     */
    private String userName;
}
