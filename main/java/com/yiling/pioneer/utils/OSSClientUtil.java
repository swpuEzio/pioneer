package com.yiling.pioneer.utils;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.event.ProgressEvent;
import com.aliyun.oss.event.ProgressEventType;
import com.aliyun.oss.event.ProgressListener;
import com.aliyun.oss.model.*;
import com.yiling.pioneer.constant.SmsConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @Author: xc
 * @Date: 2019/1/8 16:32
 * @Description: 阿里云链接
 **/
public class OSSClientUtil {
    public static final Logger logger = LoggerFactory.getLogger(OSSClientUtil.class);


    /**
     * 创建OSSClient链接对象
     * @return
     */
    public static OSSClient getOSSClient(){
        return new OSSClient(SmsConstant.ENDPOINT,SmsConstant.ACCESS_KEY_ID, SmsConstant.SECRET);
    }
    /**
     * 创建模拟文件夹
     *
     * @param ossClient oss连接
     * @param bucketName 存储空间
     * @param folder 模拟文件夹名如"qj_nanjing/"
     * @return 文件夹名
     */
    public static String createFolder(OSSClient ossClient, String bucketName, String folder) {
        // 文件夹名
        final String keySuffixWithSlash = folder;
        // 判断文件夹是否存在，不存在则创建
        if (!ossClient.doesObjectExist(bucketName, keySuffixWithSlash)) {
            // 创建文件夹
            ossClient.putObject(bucketName, keySuffixWithSlash, new ByteArrayInputStream(new byte[0]));
            logger.info("创建文件夹成功");
            // 得到文件夹名
            OSSObject object = ossClient.getObject(bucketName, keySuffixWithSlash);
            String fileDir = object.getKey();
            return fileDir;
        }
        return keySuffixWithSlash;
    }

//    /**
//     * 上传至oss
//     * @param ossClient oss链接
//     * @param bucketName
//     * @param folder
//     * @return resultStr 返回的唯一MD5数字签名
//     */
//
//    public static  String uploadObject2OSS(OSSClient ossClient,
//                                           InputStream is,
//                                           String fileName,
//                                           Long fileSize ,
//                                           String contentType,
//                                           String bucketName,
//                                           String folder) {
//        String resultStr = null;
//        String finalName = fileName + "." + contentType.substring(6);
//        System.out.println(finalName);
//        try {
////            //以输入流的形式上传文件
////            InputStream is = new FileInputStream(file);
////            //文件名
////            String fileName = file.getName();
////            //文件大小
////            Long fileSize = file.length();
//            //创建上传Object的Metadata
//            ObjectMetadata metadata = new ObjectMetadata();
//            //上传的文件的长度
//            metadata.setContentLength(is.available());
//            //指定该Object被下载时的网页的缓存行为
//            metadata.setCacheControl("no-cache");
//            //指定该Object下设置Header
//            metadata.setHeader("Pragma", "no-cache");
//            //指定该Object被下载时的内容编码格式
//            metadata.setContentEncoding("utf-8");
//            //文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
//            //如果没有扩展名则填默认值application/octet-stream
//            metadata.setContentType(contentType);
//            //指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
//            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
//            //上传文件   (上传文件流的形式)
//            PutObjectResult putResult = ossClient.putObject(bucketName, folder +"/" + finalName, is, metadata);
//            //解析结果
//            resultStr = "https://pioneer-test.oss-cn-beijing.aliyuncs.com/" + folder +"/" + finalName;
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
//        }
//        System.out.println();
//        return resultStr;
//    }
//
//    /**
//     * 获得url链接
//     *
//     * @param
//     * @return
//     */
//    public static String getUrl(String username) {
//
////          此方法返回的url是对象的urlNoSuchKey无法链接到图像，不是图片的url
////
////        // 设置URL过期时间为10年  3600l* 1000*24*365*10
////        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
////        // 生成URL
////        URL url = ossClient.generatePresignedUrl(BUCKET_NAME, key, expiration);
////        if (url != null) {
////            return url.toString();
////        }
////        return null;

//        String url = "https://xzio.oss-cn-beijing.aliyuncs.com/static/user/" + username;
//        return url;
//    }
    public static JSONObject fileUpload(File file, String key, HttpServletRequest request)
    {
        OSSClient ossClient = new OSSClient(SmsConstant.ENDPOINT,SmsConstant.ACCESS_KEY_ID, SmsConstant.SECRET);

        try {
            /*
             * Determine whether the bucket exists
             */
            if (!ossClient.doesBucketExist(SmsConstant.BUCKET_NAME)) {
                /*
                 * Create a new OSS bucket
                 */
                System.out.println("Creating bucket " + SmsConstant.BUCKET_NAME + "\n");
                ossClient.createBucket(SmsConstant.BUCKET_NAME);
                CreateBucketRequest createBucketRequest= new CreateBucketRequest(SmsConstant.BUCKET_NAME);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(createBucketRequest);
            }

            /*
             * List the buckets in your account
             */
            System.out.println("Listing buckets");

            ListBucketsRequest listBucketsRequest = new ListBucketsRequest();
            listBucketsRequest.setMaxKeys(500);

            for (Bucket bucket : ossClient.listBuckets()) {
                System.out.println(" - " + bucket.getName());
            }

            /*
             * Upload an object to your bucket
             */
            System.out.println("Uploading a new object to OSS from a file\n");
			/*
			        这里用带进度条的OSS上传
			        将session传入PutObjectProgressListener的构造中!官网例子是没有这个操作的
			        注意new PutObjectRequest()的第三个参数是File而不是官网介绍的FileInputStream,否则获取不到进度.
	       */
            ossClient.putObject(new PutObjectRequest(SmsConstant.BUCKET_NAME, SmsConstant.FILEDIR + key, file).
                    withProgressListener(new PutObjectProgressListener(request.getSession())));
        } catch (OSSException oe) {
            oe.printStackTrace();
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message: " + oe.getErrorCode());
            System.out.println("Error Code:       " + oe.getErrorCode());
            System.out.println("Request ID:      " + oe.getRequestId());
            System.out.println("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            ce.printStackTrace();
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ce.getMessage());
            System.out.println(ce.getMessage());
        } catch(Exception e){
            e.printStackTrace();
        }finally {
            ossClient.shutdown();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key",key + file.getName());
        jsonObject.put("url","https://pioneer-test.oss-cn-beijing.aliyuncs.com/" + SmsConstant.FILEDIR + key);
        return jsonObject;
    }

    /**
     * The uploading progress listener. Its progressChanged API is called by the SDK when there's an update.
     */
    static class PutObjectProgressListener implements ProgressListener {

        private HttpSession session;
        private long bytesWritten = 0;
        private long totalBytes = -1;
        private boolean succeed = false;
        private int percent = 0;

        //构造方法中加入session
        public PutObjectProgressListener() {
        }
        public PutObjectProgressListener(HttpSession mSession) {
            this.session = mSession;
            session.setAttribute("upload_percent", percent);
        }

        @Override
        public void progressChanged(ProgressEvent progressEvent) {
            long bytes = progressEvent.getBytes();
            ProgressEventType eventType = progressEvent.getEventType();
            switch (eventType) {
                case TRANSFER_STARTED_EVENT:
                    System.out.println("Start to upload......");
                    break;

                case REQUEST_CONTENT_LENGTH_EVENT:
                    this.totalBytes = bytes;
                    System.out.println(this.totalBytes + " bytes in total will be uploaded to OSS");
                    break;

                case REQUEST_BYTE_TRANSFER_EVENT:
                    this.bytesWritten += bytes;
                    if (this.totalBytes != -1) {
                        int percent = (int)(this.bytesWritten * 100.0 / this.totalBytes);
                        //将进度percent放入session中
                        session.setAttribute("upload_percent", percent);
                        System.out.println(bytes + " bytes have been written at this time, upload progress: " +
                                percent + "%(" + this.bytesWritten + "/" + this.totalBytes + ")");
                    } else {
                        System.out.println(bytes + " bytes have been written at this time, upload ratio: unknown" +
                                "(" + this.bytesWritten + "/...)");
                    }
                    break;

                case TRANSFER_COMPLETED_EVENT:
                    this.succeed = true;
                    System.out.println("Succeed to upload, " + this.bytesWritten + " bytes have been transferred in total");
                    break;

                case TRANSFER_FAILED_EVENT:
                    System.out.println("Failed to upload, " + this.bytesWritten + " bytes have been transferred");
                    break;

                default:
                    break;
            }
        }

        public boolean isSucceed() {
            return succeed;
        }
    }

}
