package com.yiling.pioneer.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Seagull_gby
 * @date 2019/9/8 10:36
 * Description: 违规用户封装
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserViolation {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户违规次数
     */
    private Integer violateNum;
}
