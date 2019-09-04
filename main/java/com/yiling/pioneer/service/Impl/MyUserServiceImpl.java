package com.yiling.pioneer.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.bcel.internal.util.BCELifier;
import com.yiling.pioneer.mapper.MyUserMapper;
import com.yiling.pioneer.model.MyUser;
import com.yiling.pioneer.service.MyUserService;
import com.yiling.pioneer.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: xc
 * @Date: 2019/7/9 11:37
 * @Description:
 **/
@Service
public class MyUserServiceImpl implements MyUserService {

    @Autowired
    MyUserMapper myUserMapper;
    @Override
    public MyUser getUserAllInfoByUserName(String username) {
        return myUserMapper.getUserAllInfoByUserName(username);
    }

    /**
     * 注册用户
     *
     * @param IDCard   身份证号
     * @param name     姓名
     * @param phoneNum 电话号码
     * @param nickname 昵称
     * @return
     */
    @Override
    public JSONObject addUser(String IDCard, String name, String phoneNum, String nickname) {
        List<Map> existed = myUserMapper.getAllPhoneAndNickname();
        JSONObject jsonObject = new JSONObject();
        MyUser user = new MyUser();
        for (Map m: existed){
            if (phoneNum.equals(m.get("username"))){
                jsonObject.put("status",401);
                jsonObject.put("message","手机号已被注册");
                return jsonObject;
            }else if (nickname.equals(m.get("nickname"))){
                jsonObject.put("status",402);
                jsonObject.put("message","用户名已存在");
                return jsonObject;
            }
        }
        user.setUsername(phoneNum);
        String sixID = IDCard.substring(12,18);
        String password = new BCryptPasswordEncoder().encode(sixID);
        user.setPassword(password);
        user.setUID(Integer.parseInt(TimeUtils.TimeCrateID()));
        user.setRole("ROLE_USER");
        user.setName(name);
        user.setIDCard(IDCard);
        user.setNickname(nickname);
        user.setAvatarUrl("https://xzio.oss-cn-beijing.aliyuncs.com/static/img/defult.jpg");
        if (myUserMapper.addUser(user)){
            jsonObject.put("status",200);
            jsonObject.put("message","注册成功");
        }else{
            jsonObject.put("status",500);
            jsonObject.put("message","注册失败，写入数据库时出错");
        }

        return jsonObject;
    }

    /**
     * 更新密码
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public JSONObject updatePassword(String username, String password) {
        String BCpassword = new BCryptPasswordEncoder().encode(password);
        JSONObject jsonObject = new JSONObject();
        try {
            if(myUserMapper.updatePasswoed(username,BCpassword)){
                jsonObject.put("status",200);
                jsonObject.put("message","修改成功");
            }else {
                jsonObject.put("status",500);
                jsonObject.put("message","修改失败，更新数据库失败");
            }
            return jsonObject;
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("status",501);
            jsonObject.put("message","数据库异常");
            return jsonObject;
        }
    }
}
