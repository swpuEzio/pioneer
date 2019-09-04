package com.yiling.pioneer.model;

import lombok.Data;

/**
 * @Author: xc
 * @Date: 2019/8/27 13:52
 * @Description:
 **/
@Data
public class ArticleLike {

    private String articleID;
    private String uID;
    private int status;

    public ArticleLike(String articleID, String uID, int status) {
        this.articleID = articleID;
        this.uID = uID;
        this.status = status;
    }
}
