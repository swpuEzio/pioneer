package com.yiling.pioneer;


import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.yiling.pioneer.constant.SmsConstant;
import com.yiling.pioneer.mapper.MyUserMapper;
import com.yiling.pioneer.model.MyUser;

import com.yiling.pioneer.service.MyUserService;
import com.yiling.pioneer.service.RedisService;
import com.yiling.pioneer.utils.OSSClientUtil;
import com.yiling.pioneer.utils.SendSms;
import com.yiling.pioneer.utils.TimeUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PioneerApplicationTests {

	@Autowired
	MyUserMapper myUserMapper;
	@Autowired
	MyUserService myUserService;
	@Autowired
	RedisService redisService;
	@Test
	public void contextLoads() {
	}
	@Test
	public void time(){

		String i =TimeUtils.TimeCrateID();
		System.out.println(i);
	}
	@Test
	public void getInfo(){
		List<Map> list= myUserMapper.getAllPhoneAndNickname();
		System.out.println(list);
	}
	@Test
	public void six(){
		String idNUm = "510683199902120330";
		String id = idNUm.substring(12,18);
		System.out.println(id);
	}
	@Test
	public void addUser() {
		String IDCard = "510683199902120330";
		String name = "熊呈";
		String phoneNum = "15680972017";
		String nickname = "恕己";
		List<Map> existed = myUserMapper.getAllPhoneAndNickname();
		JSONObject jsonObject = new JSONObject();
		MyUser user = new MyUser();
		for (Map m: existed){
			if (phoneNum.equals(m.get("username"))){
				jsonObject.put("status",401);
				jsonObject.put("message","手机号已被注册");
				System.out.println(jsonObject);
				return ;
			}else if (nickname.equals(m.get("nickname"))){
				jsonObject.put("status",402);
				jsonObject.put("message","用户名已存在");
				System.out.println(jsonObject);
				return;
			}
		}
		user.setUsername(phoneNum);
		String sixID = IDCard.substring(12,18);
		String password = new BCryptPasswordEncoder().encode(sixID);
		user.setPassword(password);
		user.setUID(Integer.parseInt(TimeUtils.TimeCrateID()));
		user.setRole("USER");
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

		System.out.println(jsonObject);
	}
	@Test
	public void auth(){
		String roleStr = "ROLE_USER";
		String[] roles = roleStr.split(",");
		List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
		for (String role : roles) {
			simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role));
		}
		System.out.println(simpleGrantedAuthorities.toString());
	}
	@Test
	public void rand(){
		JSONObject jsonObject = SendSms.sendMsg("15680972017",String.valueOf((int)((Math.random()*9+1)*100000)));
		System.out.println(jsonObject.toString());
	}
	@Test
	public void getMsgCode(){
		String phone = "15680972017";
		int rand =(int)((Math.random()*9+1)*100000);
		String code = String.valueOf(rand);
		JSONObject msgJson = SendSms.sendMsg(phone,code);
		System.out.println(msgJson.get("status"));
		if (msgJson.get("status").toString().equals("200")){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status",200);
			jsonObject.put("message",msgJson.get("message"));
			jsonObject.put("code",code);
			jsonObject.put("createTime", System.currentTimeMillis());
			System.out.println(jsonObject.toString());

		}else {
			System.out.println("不为200-" + msgJson.toString());

		}
	}
	@Test
	public void updatePassword() {
		String username="15680972017";
		String password="123456";
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
			System.out.println(jsonObject);
		}catch (Exception e){
			e.printStackTrace();
			jsonObject.put("status",501);
			jsonObject.put("message","数据库异常");
			System.out.println(jsonObject);
		}
	}
	@Test
	public void createFile(){
		OSSClient ossClient = OSSClientUtil.getOSSClient();
		String fileDir = OSSClientUtil.createFolder(ossClient, SmsConstant.BUCKET_NAME,SmsConstant.FILEDIR + "熊呈/");
		System.out.println(fileDir);
	}
	@Test
	public void likeTest(){
		String username = "15680972017";
		MyUser currentUser = myUserService.getUserAllInfoByUserName(username);
		redisService.saveLiked2Redis(String.valueOf(currentUser.getUID()),"123456");

	}
}
