package com.yiling.pioneer.service.admin.impl;

import com.alibaba.fastjson.JSONObject;
import com.yiling.pioneer.dto.entity.UserFrozen;
import com.yiling.pioneer.dto.entity.UserViolation;
import com.yiling.pioneer.dto.entity.UserViolationReason;
import com.yiling.pioneer.dto.json.JsonContext;
import com.yiling.pioneer.dto.json.UserJsonResult;
import com.yiling.pioneer.mapper.AdminUserMapper;
import com.yiling.pioneer.service.admin.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Seagull_gby
 * @date 2019/9/8 10:48
 * Description: 管理端用户操作实现
 */

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Override
    public JSONObject getViolationUsers() {
        JSONObject violateUserJSON = new JSONObject();

        /* 从数据库中查询所有违规次数大于0的用户 */
        List<UserViolation> userViolations = new ArrayList<>();
        userViolations = adminUserMapper.queryViolateUser();

        /* JSON封装 */
        JsonContext<List<UserViolation>> jsonContext = new JsonContext<>(new UserJsonResult<>());
        violateUserJSON = jsonContext.success(userViolations);

        return violateUserJSON;
    }

    @Override
    public JSONObject getViolationReasonOfUser(Long userId) {
        JSONObject violationReasonJSON = new JSONObject();

        /* 传入用户ID，从数据库中查询该用户所有被举报原因集合 */
        List<UserViolationReason> userViolationReasons = new ArrayList<>();
        userViolationReasons = adminUserMapper.queryViolationReasonOfUser(userId);

        /* JSON封装 */
        JsonContext<List<UserViolationReason>> jsonContext = new JsonContext<>(new UserJsonResult<>());
        if (userViolationReasons.isEmpty()) {
            violationReasonJSON = jsonContext.fail("该用户未被举报过或该用户不存在！");
        } else {
            violationReasonJSON = jsonContext.success(userViolationReasons);
        }

        return violationReasonJSON;
    }

    @Override
    public JSONObject getFrozenUsers() {
        JSONObject frozenUserJSON = new JSONObject();

        /* 从数据库中取出所有被冻结的用户 */
        List<UserFrozen> userFrozens = new ArrayList<>();
        userFrozens = adminUserMapper.queryFrozenUsers();

        /* JSON封装 */
        JsonContext<List<UserFrozen>> jsonContext = new JsonContext<>(new UserJsonResult<>());
        frozenUserJSON = jsonContext.success(userFrozens);

        return frozenUserJSON;
    }

    @Override
    public JSONObject updateUserPermissions(String userName) {
        JSONObject roleUpdateJSON = new JSONObject();

        /* 更改数据库中用户权限为 BREAKER，返回更新数目 */
        int updateNum = adminUserMapper.updateUserRoleOfBreaker(userName);

        /* JSON封装 */
        JsonContext jsonContext = new JsonContext(new UserJsonResult());
        if (updateNum >= 1) {
            roleUpdateJSON = jsonContext.success(null);
        } else {
            roleUpdateJSON = jsonContext.fail("该用户已被冻结或该用户不存在！");
        }

        return roleUpdateJSON;
    }
}
