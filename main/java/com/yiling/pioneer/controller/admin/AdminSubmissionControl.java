package com.yiling.pioneer.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.yiling.pioneer.service.admin.AdminSubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Seagull_gby
 * @date 2019/9/5 14:53
 * Description: 管理端用户投稿控制层
 */

@RestController
@RequestMapping("/admin")
public class AdminSubmissionControl {

    @Autowired
    private AdminSubmissionService adminSubmissionService;

    /**
     * 获得用户全部投稿
     * @return JSON
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/getUserSubmissions")
    public JSONObject getUserSubmissions() {
        JSONObject userSubmissionJSON = new JSONObject();
        userSubmissionJSON = adminSubmissionService.getAllUserSubmissions();

        return userSubmissionJSON;
    }

    /**
     * 获得某文章的投稿信息
     * @param request 请求域（包含 文章编号）
     * @return JSON
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/getSubmissionMessage")
    public JSONObject getSubmissionMessage(HttpServletRequest request) {
        Long articleId = Long.valueOf(request.getParameter("articleId"));

        JSONObject submissionContentJSON = new JSONObject();
        submissionContentJSON = adminSubmissionService.getSubmissionContentOfId(articleId);

        return submissionContentJSON;
    }

    /**
     * 投稿审核
     * @param request 请求域（包含 文章编号，审核结果(1 通过，2 未通过)）
     * @return JSON
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping("/auditSubmission")
    public JSONObject auditSubmission(HttpServletRequest request) {
        Long articleId = Long.valueOf(request.getParameter("articleId"));
        Integer auditResult = Integer.valueOf(request.getParameter("auditResult"));

        JSONObject submissionAuditResultJSON = new JSONObject();
        submissionAuditResultJSON = adminSubmissionService.auditSubmission(articleId, auditResult);

        return submissionAuditResultJSON;
    }
}
