package com.yiling.pioneer.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.yiling.pioneer.config.AlipayConfig;
import com.yiling.pioneer.mapper.CompetitionMapper;
import com.yiling.pioneer.mapper.GameRecordMapper;
import com.yiling.pioneer.mapper.MyUserMapper;
import com.yiling.pioneer.mapper.OrderMapper;
import com.yiling.pioneer.model.Competition;
import com.yiling.pioneer.model.GameRecord;
import com.yiling.pioneer.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author: xc
 * @Date: 2019/9/10 18:46
 * @Description:
 **/
@RestController
public class AlipayController {

    @Autowired
    CompetitionMapper competitionMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    GameRecordMapper gameRecordMapper;
    @Autowired
    MyUserMapper myUserMapper;
    @PostMapping("/AliPay")
    public String goPay(HttpServletRequest request){
        String clientId = request.getParameter("cID");
        Competition current = competitionMapper.getPayInfoByCID(clientId);
        System.out.println(clientId);
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        //商户订单号，商户网站订单系统中唯一订单号，必填
        Date date = new Date();
        String out_trade_no = Long.toString(date.getTime());
        //付款金额，必填
        String total_amount = String.valueOf(current.getAmount());
        //订单名称，必填
        String subject = current.getTitle();
        //商品描述，可空
        String body = current.getPlace();
        // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        String timeout_express = "1c";
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"timeout_express\":\""+ timeout_express +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求
        try {
            String result = alipayClient.pageExecute(alipayRequest).getBody();
            System.out.println(result);
            Order order = new Order();
            order.setOrder_no(out_trade_no);
            order.setOrder_amount(Float.valueOf(total_amount));
            order.setCompetition_id(clientId);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    order.setCreate_time(sdf.format(new Date()));
		    boolean flag = orderMapper.addOrder(order);
		    if (!flag){
		        return "创建订单失败";
            }
            return result;
        }catch (Exception e){
            System.out.println("订单请求错误");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status",500);
            return "fail";
        }


    }
    @GetMapping("/return_url")
    public JSONObject return_url(HttpServletRequest request,@AuthenticationPrincipal Principal principal) throws Exception{
        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        System.out.println("map信息：" + requestParams.toString());
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";

            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            System.out.println("同步验签---- " + valueStr);
            params.put(name, valueStr);
        }
        System.out.println(params.size());
        try {
            boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
            if(signVerified) {
                //商户订单号
                String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

                //支付宝交易号
                String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

                //付款金额
                String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

                JSONObject jsonObject = new JSONObject();

                if(orderMapper.paySuccess(trade_no,out_trade_no)){
                    String cID = orderMapper.getCID(out_trade_no);
                    GameRecord gameRecord = new GameRecord();
                    gameRecord.setCID(Integer.parseInt(cID));
                    gameRecord.setUID(Integer.parseInt(myUserMapper.getUIDByUsername(principal.getName())));
                    //测试
                    //gameRecord.setUID(26659526);
                    if (gameRecordMapper.addRecord(gameRecord)){
                        jsonObject.put("status",200);
                        jsonObject.put("out_trade_no",out_trade_no);
                        jsonObject.put("trade_no",trade_no);
                        jsonObject.put("total_amount",total_amount);
                    }else {
                        jsonObject.put("status",400);
                    }


                }else {
                    jsonObject.put("status",500);
                }
                System.out.println("trade_no:"+trade_no+"<br/>out_trade_no:"+out_trade_no+"<br/>total_amount:"+total_amount);
                return jsonObject;
            }else {
                System.out.println("验签失败");
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("status",501);
                return jsonObject;
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("验证错误");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status",502);
            return jsonObject;
        }


        //——请在这里编写您的程序（以下代码仅作参考）——


    }
    @GetMapping("/notify_url")
    public JSONObject notify_url(HttpServletRequest request,@AuthenticationPrincipal Principal principal) throws Exception{
        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        System.out.println("map信息：" + requestParams.toString());
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";

            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            System.out.println("同步验签---- " + valueStr);
            params.put(name, valueStr);
        }
        System.out.println(params.size());
        try {
            boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
            if(signVerified) {
                //商户订单号
                String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

                //支付宝交易号
                String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

                //付款金额
                String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

                JSONObject jsonObject = new JSONObject();

                if(orderMapper.paySuccess(trade_no,out_trade_no)){
                    String cID = orderMapper.getCID(out_trade_no);
                    GameRecord gameRecord = new GameRecord();
                    gameRecord.setCID(Integer.parseInt(cID));
                    gameRecord.setUID(Integer.parseInt(myUserMapper.getUIDByUsername(principal.getName())));
                    //测试
                    //gameRecord.setUID(26659526);
                    if (gameRecordMapper.addRecord(gameRecord)){
                        jsonObject.put("status",200);
                        jsonObject.put("out_trade_no",out_trade_no);
                        jsonObject.put("trade_no",trade_no);
                        jsonObject.put("total_amount",total_amount);
                    }else {
                        jsonObject.put("status",400);
                    }


                }else {
                    jsonObject.put("status",500);
                }
                System.out.println("trade_no:"+trade_no+"<br/>out_trade_no:"+out_trade_no+"<br/>total_amount:"+total_amount);
                return jsonObject;
            }else {
                System.out.println("验签失败");
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("status",501);
                return jsonObject;
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("验证错误");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status",502);
            return jsonObject;
        }
    }
}
