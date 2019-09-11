package com.yiling.pioneer.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

import org.springframework.context.annotation.Configuration;

import java.io.FileWriter;
import java.io.IOException;
@Configuration
public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016101100664086";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCt1q2n0XAgVyp7gV5sQhLDxVOWDMJH8k1kPdhZvQxehW2q8IGdUsYYmHxa0fCvN5dY734CUQz+l4u3jUuC94CB1mHdWKXFBUW4/8gJKdGQQREhJvwKidFLMs8KLw8/bBu20H8qlk0Oyh5ypILvATwQ4MkGggspVQWk8UtsvPhlY0LxZy+6oOmvNWBunYEi5BLjb466Mz9rtbOyfom80ZtoR7tnqxy7KJGjAziSEu0eNPuxNosFLnlDy5I4ernOvGKAbxHttOOsIr2iTjw1al2WHY2c+SpKKgHbSr+qUBaMhfs8NT1+fcKZUPRyVv7vuJGRVyd4fWpZ8zvLRbAftC8xAgMBAAECggEATHbgfa+/LUqKEjdObDoH/PrgEGFeV70503zsnFRui2kPWyBdMvFE127DTEOKJdhDB0iY2bYDDD1mpH9PFrV9Xh8Jfw5OJEJ0b7J6r6UoW9ZzDW0TciM632Pi8+zNPS11Z02Akh/4lyfKiszYFv8pB5eIdQdgl3ahe/LzJUpdzGJKaxdXJhRmNyKtjmc+DV+NfcnD1UdQcLpPmDnH57Q4Yr76r4rJ/sjOLCK/r6HMUTCeOkRps36xp6LV5gNRcw030g6tQT5NcK3lKoIP1OMKrXsX/b1GjzzC/pTnWKtpCrClcJB5LU1PAfeUOjv2jCDc6P8KATJuqj5xSm15jmhPPQKBgQDTNlmoHpK5iy/aAH91/cUuFzztNWQQaMMUDcWpIMyNYi+h6PWEsJiM8BL+eOVWu/ry2+L4W7D3ZiF2+y/Ox+RbIEWyYOnjqrmWAUs8IjtL/Z4vOLK/TShvi1JNEpCu53YSRmznURK80SoVgTY1a7/x9jMSi16UnltxUWTBR6xvgwKBgQDSs39k6NT3zDPgIcprjAOvVWi5zoaLCljzk+huf7CL6juJv+EsyR1Vy/t2YpbifX/eF5iuhW6BtkQU7TuBlaMsPsr84zs+iKHSs1bUbWueysEyNl57SdjH8qwVlN9eWtUQ9M51J6HrYuiAPcG7R3bZ+GeyxrMCIS0+/X/i5orUOwKBgEg53sDx64TNhwiKgQvNQv927NvFFTnTSVpzNzOVxr1WZSl96297oD8Z3bTtjKdzUuZ3sQ7y8/o2D52LWhbhHkLXL15Ha9PxztLwEFU484QR72UAg4eNkBVM1FEM1zMobaTkNluHytuMv1JpRCtSdGn+ogpmZGp4GJcQmV7K7rmXAoGBALDeqOQmZxAURL+y3d7ly/oU8jJ3YY26A+2EjaqkWak52PzLK9SShqOM9evs2sgJnZrrp+lobNlKDWCbvEYNPMpZc8BRZ1wZCe57STpNF/hWL1NdPHa2CNnfpgD/1Xm5Of5f4ieKO2wCvTVZohT56pUS5sm17nKtMvQbjI0GBigdAoGAVPYtgi1h1/OyCSsN9Bno/5xsDLkMO6xfiArElA6GGFEd716pBK+5jn1zik1hGlB7jTxOJ7MWKOXM6eZRekJ64H8fkDb0a7ke8Q8+LI73W4GAnObf8vHd52+XN9K+goBUrq5SdwV6bl5ujknLDko1jMduCgNCjpjiLA4T2tKaF4E=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArdatp9FwIFcqe4FebEISw8VTlgzCR/JNZD3YWb0MXoVtqvCBnVLGGJh8WtHwrzeXWO9+AlEM/peLt41LgveAgdZh3VilxQVFuP/ICSnRkEERISb8ConRSzLPCi8PP2wbttB/KpZNDsoecqSC7wE8EODJBoILKVUFpPFLbLz4ZWNC8WcvuqDprzVgbp2BIuQS42+OujM/a7Wzsn6JvNGbaEe7Z6scuyiRowM4khLtHjT7sTaLBS55Q8uSOHq5zrxigG8R7bTjrCK9ok48NWpdlh2NnPkqSioB20q/qlAWjIX7PDU9fn3CmVD0clb+77iRkVcneH1qWfM7y0WwH7QvMQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:1022/notify_url";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:1022/return_url";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}