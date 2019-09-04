package com.yiling.pioneer.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.yiling.pioneer.mapper.MediaMapper;
import com.yiling.pioneer.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: xc
 * @Date: 2019/8/24 15:44
 * @Description:
 **/
@Service
public class MediaServiceImpl implements MediaService {
    @Autowired
    MediaMapper mediaMapper ;
    /**
     * 插入记录
     *
     * @param name 文件名
     * @param url  oss外网链接
     * @return
     */
    @Override
    public boolean add(String name, String url) {
        return mediaMapper.add(name,url);
    }

    @Override
    public boolean setArticleId(String name, String articleId) {
        return mediaMapper.setArticleId(name,articleId);
    }


}
