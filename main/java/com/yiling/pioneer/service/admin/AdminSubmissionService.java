package com.yiling.pioneer.service.admin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @author Seagull_gby
 * @date 2019/9/6 11:09
 * Description: 管理端投稿操作接口
 */

@Service
public interface AdminSubmissionService {

    /**
     * 获取所有用户投稿
     * @return JSON
     */
    public JSONObject getAllUserSubmissions();

    /**
     * 根据文章编号获得投稿详细信息
     * @param articleId 文章编号
     * @return JSON
     */
    public JSONObject getSubmissionContentOfId(Long articleId);

    /**
     * 投稿审核
     * @param articleId 文章编号
     * @param auditResult 审核结果（1 为通过， 2 为未通过）
     * @return JSON
     */
    public JSONObject auditSubmission(Long articleId, Integer auditResult);
}
