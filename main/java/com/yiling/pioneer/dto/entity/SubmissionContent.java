package com.yiling.pioneer.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Seagull_gby
 * @date 2019/9/6 17:14
 * Description: 用户投稿详细内容
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionContent {

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 文章标题
     */
    private String articleTitle;

    /**
     * 文章内容
     */
    private String articleContent;

    /**
     * 文章中视频URL
     */
    private String videoUrl;
}
