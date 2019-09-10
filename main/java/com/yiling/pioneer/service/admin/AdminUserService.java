package com.yiling.pioneer.service.admin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @author Seagull_gby
 * @date 2019/9/8 10:46
 * Description: 管理端用户操作接口
 */

@Service
public interface AdminUserService {

    /**
     * 获得违规用户列表
     * @return JSON
     */
    public JSONObject getViolationUsers();

    /**
     * 获得某用户所有被举报原因
     * @param userId 用户ID
     * @return JSON
     */
    public JSONObject getViolationReasonOfUser(Long userId);

    /**
     * 获得被冻结的用户列表
     * @return JSON
     */
    public JSONObject getFrozenUsers();

    /**
     * 冻结用户（更改用户权限）
     * @param userName 用户昵称
     * @return JSON
     */
    public JSONObject updateUserPermissions(String userName);

}
