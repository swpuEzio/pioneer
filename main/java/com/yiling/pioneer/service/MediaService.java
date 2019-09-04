package com.yiling.pioneer.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @Author: xc
 * @Date: 2019/8/24 15:41
 * @Description:
 **/
@Service
public interface MediaService {
    /**
     * 插入记录
     * @param name 文件名
     * @param url oss外网链接
     * @return
     */
    boolean add(String name, String url);
    /**
     * 根据文件名插入文章ID
     * @param name 视频名
     * @param articleId 文章ID
     * @return Boolean
     */
    boolean setArticleId( String name,String articleId);
}
