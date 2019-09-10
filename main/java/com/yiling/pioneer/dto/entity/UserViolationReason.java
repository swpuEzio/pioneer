package com.yiling.pioneer.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Seagull_gby
 * @date 2019/9/8 11:31
 * Description: 用户举报原因封装
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserViolationReason {

    /**
     * 举报原因
     */
    private String violationReason;

    /**
     * 文章编号
     */
    private Long articleId;
}
