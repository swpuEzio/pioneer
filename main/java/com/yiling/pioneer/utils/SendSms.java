package com.yiling.pioneer.utils;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.yiling.pioneer.constant.SmsConstant;

/**
 * @Author: xc
 * @Date: 2019/7/11 15:37
 * @Description: 发送验证码
 **/
public class SendSms {
    public static JSONObject sendMsg(String phoneNum,String code) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-beijing", SmsConstant.ACCESS_KEY_ID, SmsConstant.SECRET);
        IAcsClient client = new DefaultAcsClient(profile);
        JSONObject jsonObject = new JSONObject();
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-beijing");
        request.putQueryParameter("PhoneNumbers", phoneNum);
        request.putQueryParameter("SignName", "咕噜橙");
        request.putQueryParameter("TemplateCode", "SMS_170330746");
        JSONObject codeJson = new JSONObject();
        codeJson.put("code",code);
        request.putQueryParameter("TemplateParam", codeJson.toString());
        try {
            CommonResponse response = client.getCommonResponse(request);
            JSONObject responseJson = JSONObject.parseObject(response.getData());
            if ("OK".equals(responseJson.get("Code"))){
                jsonObject.put("status",200);
                jsonObject.put("message",responseJson.get("Message"));
            }else {
                jsonObject.put("status",401);
                jsonObject.put("message",responseJson.get("Message"));
            }
            return jsonObject;
        } catch (ServerException e) {
            e.printStackTrace();
            jsonObject.put("status",500);
            jsonObject.put("message","服务器错误");
        } catch (ClientException e) {
            e.printStackTrace();
            jsonObject.put("status",501);
            jsonObject.put("message","链接错误");
        }

        return jsonObject;
    }
}

