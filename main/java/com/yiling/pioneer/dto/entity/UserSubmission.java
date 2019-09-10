package com.yiling.pioneer.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Seagull_gby
 * @date 2019/9/5 14:57
 * Description: 用户投稿实体类
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSubmission {

    /**
     * 文章编号
     */
    private long articleId;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 文章标题
     */
    private String articleTitle;
}
