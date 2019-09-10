package com.yiling.pioneer.service.admin.impl;

import com.alibaba.fastjson.JSONObject;
import com.yiling.pioneer.dto.entity.SubmissionContent;
import com.yiling.pioneer.dto.entity.UserSubmission;
import com.yiling.pioneer.dto.json.JsonContext;
import com.yiling.pioneer.dto.json.SubmissionJsonResult;
import com.yiling.pioneer.mapper.SubmissionMapper;
import com.yiling.pioneer.service.admin.AdminSubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Seagull_gby
 * @date 2019/9/6 11:52
 * Description: 用户投稿操作实现
 */

@Service
public class AdminSubmissionServiceImpl implements AdminSubmissionService {

    @Autowired
    private SubmissionMapper submissionMapper;

    @Override
    public JSONObject getAllUserSubmissions() {
        JSONObject userSubmissionJSON = new JSONObject();

        /* 从数据库中获得所有用户投稿 */
        List<UserSubmission> userSubmissions = new ArrayList<>();
        userSubmissions = submissionMapper.queryUserSubmission();

        /* 转换为JSON格式 */
        JsonContext<List<UserSubmission>> jsonContext = new JsonContext<>(new SubmissionJsonResult<>());
        userSubmissionJSON = jsonContext.success(userSubmissions);

        return userSubmissionJSON;
    }

    @Override
    public JSONObject getSubmissionContentOfId(Long articleId) {
        JSONObject submissionContentJSON = new JSONObject();

        /* 从数据库中根据文章编号查找相应投稿 */
        SubmissionContent submissionContent = new SubmissionContent();
        submissionContent = submissionMapper.querySubmissionContent(articleId);

        /* 转换为JSON格式 */
        JsonContext<SubmissionContent> jsonContext = new JsonContext<>(new SubmissionJsonResult<>());
        submissionContentJSON = jsonContext.success(submissionContent);

        return submissionContentJSON;
    }

    @Override
    public JSONObject auditSubmission(Long articleId, Integer auditResult) {
        JSONObject submissionAuditResultJSON = new JSONObject();

        /* 更新数据库中投稿状态信息 */
        JsonContext jsonContext = new JsonContext(new SubmissionJsonResult());
        int updateNum = submissionMapper.updateSubmissionState(articleId, auditResult);
        if(updateNum >= 1) {
            submissionAuditResultJSON = jsonContext.success(null);
        } else {
            submissionAuditResultJSON = jsonContext.fail("投稿已被审核或投稿不存在！");
        }

        return submissionAuditResultJSON;
    }
}
