package com.yiling.pioneer.controller;

import com.alibaba.fastjson.JSONObject;

import com.yiling.pioneer.service.MediaService;
import com.yiling.pioneer.service.MyUserService;
import com.yiling.pioneer.utils.*;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xc
 * @Date: 2019/7/10 11:28
 * @Description:
 **/
@RestController
public class UserController {
    @Autowired
    MyUserService myUserService;
    @Autowired
    MediaService mediaService;
    @GetMapping("/checkIDCard")
    public JSONObject checkIDCard(@RequestParam("num") String num, @RequestParam("name") String name){
        String host = "http://idcard3.market.alicloudapi.com";
        String path = "/idcardAudit";
        String method = "GET";
        String appcode = "4fdf0aab33fd4f5faaf47199077a5aaa";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("idcard", num);
        querys.put("name", name);
        JSONObject jsonObject = new JSONObject();
        System.out.println(num);
        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);





            //获取response的body
            String msg = EntityUtils.toString(response.getEntity());
            System.out.println(msg);
            jsonObject = JSONObject.parseObject(msg);
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("status",500);
            jsonObject.put("message","服务器内部错误");
            return jsonObject;
        }
    }

    @PostMapping("/register")
    public JSONObject register(@RequestParam String IDCard,
                               @RequestParam String name,
                               @RequestParam String phoneNum,
                               @RequestParam String nickname){

        return myUserService.addUser(IDCard,name,phoneNum,nickname);
    }
    @GetMapping("/getMsgCode")
    public JSONObject getMsgCode(@RequestParam("phone") String phone){
        int rand =(int)((Math.random()*9+1)*100000);
        String code = String.valueOf(rand);
        JSONObject msgJson = SendSms.sendMsg(phone,code);
        if (msgJson.get("status").toString().equals("200")){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status",200);
            jsonObject.put("message",msgJson.get("message"));
            jsonObject.put("code",code);
            jsonObject.put("createTime", System.currentTimeMillis());
            return jsonObject;
        }else {
            return msgJson;
        }
    }
    @PostMapping("/updatePassword")
    public JSONObject updatePassword(@AuthenticationPrincipal Principal principal,
                                     @RequestParam("password") String password,
                                     @RequestParam("phone") String phone){
        String username = principal.getName();
        if ( username == null){
            username = phone;
            if ( username == null){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("status",400);
                jsonObject.put("message","用户名为空");
                return jsonObject;
            }
        }
        return myUserService.updatePassword(username,password);

    }

    @PostMapping("/upVideo")
    public JSONObject up(HttpServletRequest request,
                                @RequestParam("file") MultipartFile mfile,
                                @AuthenticationPrincipal Principal principal) {
        try {
            String username = principal.getName();
            String path = request.getSession().getServletContext().getRealPath("/upLoad");
            File realFile = new File(path + mfile.getOriginalFilename());
            mfile.transferTo(realFile);
            String name = TimeUtils.TimeCrateID();
            String oldName=mfile.getOriginalFilename();
            String suffix=oldName.substring(oldName.lastIndexOf("."));
            String fileName = name + suffix;
            JSONObject jsonObject = OSSClientUtil.fileUpload(realFile,username +"/" + fileName,request);

            if (mediaService.add(name,jsonObject.getString("url"))){

                request.getSession().setAttribute("videoID",name);
                jsonObject.put("status",200);
                jsonObject.put("videoID",name);
            }else {
                jsonObject.put("status",500);

            }

            return jsonObject;
        }catch (IOException e){
            e.printStackTrace();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status",501);
            return jsonObject;
        }catch (Exception e){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status",502);
            return jsonObject;
        }

    }
    /**
     * 获取实时长传进度
     * @param request
     * @return
     */
    @RequestMapping ("/item/percent")
    @ResponseBody
    public int getUploadPercent(HttpServletRequest request){
        HttpSession session = request.getSession();
        int percent = session.getAttribute("upload_percent") == null ? 0:  (Integer)session.getAttribute("upload_percent");
        return percent;
    }

    /**
     * 重置上传进度
     * @param request
     * @return
     */
    @RequestMapping("/percent/reset")
    public void resetPercent(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("upload_percent",0);
    }

}
