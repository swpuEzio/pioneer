package com.yiling.pioneer.model;

import lombok.Data;

/**
 * @Author: xc
 * @Date: 2019/9/5 16:56
 * @Description: 举报实体类
 **/
@Data
public class ImpeachRecord {
    private int id;
    private int reporteeUID;
    private int sendUID;
    private int articleID;
    private String content;
}
 