package com.yiling.pioneer.task;

import com.yiling.pioneer.service.ArticleLikeService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: xc
 * @Date: 2019/8/30 13:28
 * @Description: 定时任务
 **/
@Slf4j
public class LikeTask extends QuartzJobBean {

    @Autowired
    ArticleLikeService articleLikeService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        log.info("LikeTask-------- {}", sdf.format(new Date()));

        //将 Redis 里的点赞信息同步到数据库里
        articleLikeService.transLikedFromRedis2DB();
//        articleLikeService.transLikedCountFromRedis2DB();
    }
}

