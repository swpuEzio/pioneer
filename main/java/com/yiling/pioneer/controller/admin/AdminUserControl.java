package com.yiling.pioneer.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.yiling.pioneer.service.admin.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Seagull_gby
 * @date 2019/9/6 19:17
 * Description: 管理端用户信息控制层
 */

@RestController
@RequestMapping("/admin")
public class AdminUserControl {

    @Autowired
    private AdminUserService adminUserService;

    /**
     * 查询所有违规用户
     * @return JSON
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/getViolationUsers")
    public JSONObject getViolationUsers() {
        JSONObject violateUserJSON = new JSONObject();
        violateUserJSON = adminUserService.getViolationUsers();

        return violateUserJSON;
    }

    /**
     * 查询某用户所有被举报记录
     * @param request 请求域（包含 用户ID）
     * @return JSON
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping("/getViolationReasonOfUser")
    public JSONObject getViolationReasonOfUser(HttpServletRequest request) {
        Long userId = Long.valueOf(request.getParameter("userId"));

        JSONObject violationReasonJSON = new JSONObject();
        violationReasonJSON = adminUserService.getViolationReasonOfUser(userId);

        return violationReasonJSON;
    }

    /**
     * 查询所有被冻结的用户
     * @return JSON
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/getFrozenUsers")
    public JSONObject getFrozenUsers() {
        JSONObject frozenUserJSON = new JSONObject();
        frozenUserJSON = adminUserService.getFrozenUsers();

        return frozenUserJSON;
    }

    /**
     * 冻结用户
     * @param request 请求域（包含 用户昵称）
     * @return JSON
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/updateUserPermissions")
    public JSONObject frozenUser(HttpServletRequest request) {
        String userName = request.getParameter("userName");

        JSONObject roleUpdateJSON = new JSONObject();
        roleUpdateJSON = adminUserService.updateUserPermissions(userName);

        return roleUpdateJSON;
    }

}
