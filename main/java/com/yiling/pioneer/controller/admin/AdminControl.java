package com.yiling.pioneer.controller.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Seagull_gby
 * @date 2019/9/5 14:51
 * Description: 管理端返回控制层
 */

@Controller
@RequestMapping("/admin")
public class AdminControl {

    /**
     * 后台页面跳转
     * @return 后台管理页面
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public String admin() {
        return "admin";
    }
}
