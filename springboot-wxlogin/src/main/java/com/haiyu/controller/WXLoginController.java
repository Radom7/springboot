package com.haiyu.controller;

import com.haiyu.conmmon.*;
import com.haiyu.model.WXSessionModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Desc:
 * @Author: liuxing
 * @Date: 2019/1/12 11:11
 * @Version 1.0
 */
@RestController
@Slf4j
public class WXLoginController {

    @Autowired
    private RedisOperator redis;

    @PostMapping("/wxLogin")
    public JSONResult wxLogin(String code) {

        log.info("wxlogin - code: " + code);

//		https://api.weixin.qq.com/sns/jscode2session?
//				appid=APPID&
//				secret=SECRET&
//				js_code=JSCODE&
//				grant_type=authorization_code

        String url = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, String> param = new HashMap<>();
        param.put("appid", WxApp.APPID);
        param.put("secret", WxApp.SECRET);
        param.put("js_code", code);
        param.put("grant_type", "authorization_code");

        String wxResult = HttpUtils.doGet(url, param);
        log.info(wxResult);

        WXSessionModel model = JsonUtils.jsonToPojo(wxResult, WXSessionModel.class);

        log.info(model.toString());
        // 存入session到redis
        redis.set("user-redis-session:" + model.getOpenid(),
                model.getSession_key(),
                1000 * 60 * 30);

        return JSONResult.ok();
    }

}
