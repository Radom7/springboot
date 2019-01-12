package com.haiyu.model;

import lombok.Data;

/**
 * @Desc: 微信请求的返回体
 * @Author: liuxing
 * @Date: 2019/1/12 11:19
 * @Version 1.0
 */
@Data
public class WXSessionModel {
    private String session_key;
    private String openid;

}
